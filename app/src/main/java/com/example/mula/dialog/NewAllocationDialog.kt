package com.example.mula.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mula.R
import com.example.mula.data.model.Allocation
import com.example.mula.data.model.Income
import com.example.mula.view_model.AllocationListViewModel


class  NewAllocationDialog( val income: Income,
    val allocation: Allocation? = null): DialogFragment() {//Third argument serves as a check if update is to be made
    //instantiate viewmodel
    lateinit var factory: ViewModelProvider.AndroidViewModelFactory
    lateinit var allocationListViewModel: AllocationListViewModel


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //instantiate viewmodel
        factory = ViewModelProvider.AndroidViewModelFactory(activity!!.applicationContext as Application)
        allocationListViewModel = ViewModelProvider(this,factory).get(AllocationListViewModel::class.java)
        // All the rest of the code goes here
        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.new_allocation_dialog, null)

        val editDescription: EditText = dialogView.findViewById(R.id.editDescription)
        val editAmount:EditText = dialogView.findViewById(R.id.editAmount)
        val btnCancel: Button = dialogView.findViewById(R.id.btnCancel)
        val btnOK:Button = dialogView.findViewById(R.id.btnOK)

        /**
         * Check if the dialog will facilitate EDITING of Allocation
        * */
        allocation?.let {
            //if null set value for editing
            editDescription.setText(it.label)
            editAmount.setText(it.amount.toString())
            editAmount.isEnabled = false
        }

        builder.setView(dialogView)
            .setTitle("Add a new allocation")
            .setCancelable(false)

        // Handle the cancel button
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnOK.setOnClickListener {
            val description = editDescription.text.toString()
            val amount = editAmount.text.toString()
            if(!description.isNullOrEmpty() && !amount.isNullOrEmpty() && description.length > 3) {
                if(allocation != null){
                    updateAllocation(description, amount.toDouble())
                }else {
                    createNewAllocation(description, amount.toDouble())
                }
            }else{
                Toast.makeText(context!!.applicationContext, "Enter a valid amount and description", Toast.LENGTH_LONG).show()
            }
        }

        return builder.create()
    }

    private fun updateAllocation(description: String, amount: Double) {
        allocation?.let{
            it.amount = amount
            it.label = description

            addAllocation(it)
        }
    }

    private fun createNewAllocation(description: String, amount: Double) {
        // Create a new allocation
        val newAllocation = Allocation()

        // Set its properties to match the
        // User's entries on the form
        newAllocation.label = description
        newAllocation.amount = amount
        newAllocation.balanceId = income.id

        addAllocation(newAllocation)
    }

    private fun addAllocation(newAllocation: Allocation) {
        //Set deducted balance
        newAllocation.deductedBalance = income.amount - newAllocation.amount
        //get balance instance
        allocationListViewModel.getIncomeById(newAllocation.balanceId).observe(this, Observer { income ->
            //get income
            var response = "Added Successfully"
            if(income.amount >= newAllocation.amount) {
                allocationListViewModel.setNewAllocation(newAllocation)

                //Subtract Value From Balance and Update
                income.amount -= newAllocation.amount

                //Set new allocation remaining income
                newAllocation.deductedBalance = income.amount

                allocationListViewModel.updateNewIncome(income)
                dismiss()
            }else{
                response = "Allocation cannot be greater than available income"
            }
            Toast.makeText(context, response,Toast.LENGTH_LONG).show()
        })
    }
}
