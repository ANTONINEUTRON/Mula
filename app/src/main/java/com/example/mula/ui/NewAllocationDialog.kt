package com.example.mula.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mula.activity.AllocationListActivity
import com.example.mula.R
import com.example.mula.data.DataManager
import com.example.mula.model.Allocation



class  NewAllocationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // All the rest of the code goes here
        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.new_allocation_dialog, null)

        val editDescription = dialogView.findViewById(R.id.editDescription) as EditText

        val editAmount = dialogView.findViewById(R.id.editAmount) as EditText

        val btnCancel = dialogView.findViewById(R.id.btnCancel) as Button

        val btnOK = dialogView.findViewById(R.id.btnOK) as Button

        builder.setView(dialogView).setMessage("Add a new allocation")

        // Handle the cancel button
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnOK.setOnClickListener {
            // Create a new allocation
            val newAllocation = Allocation()

            // Set its properties to match the
            // User's entries on the form
            newAllocation.expenseDescription = editDescription.text.toString()
            newAllocation.expenseAmount = editAmount.text.toString() //TODO: FIX THIS!!
            //TODO: REMANING BALANCE GO HERE!!

            // Get a reference to AllocationRecyclerView
            val callingActivity = activity as AllocationListActivity?

            // Pass newAllocation back to AllocationRecyclerView
            callingActivity!!.createNewAllocation(newAllocation)

            // Quit the dialog
            dismiss()

        }

        return builder.create()
    }
}
