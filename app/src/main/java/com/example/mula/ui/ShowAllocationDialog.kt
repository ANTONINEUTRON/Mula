package com.example.mula.ui

import android.view.View
import android.os.Bundle
import android.app.Dialog
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mula.R
import com.example.mula.model.Allocation


class ShowAllocationDialog: DialogFragment(){

    private var allocation: Allocation? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // All the other code goes here

        val builder = AlertDialog.Builder(this.activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.show_allocation_dialog, null)

        val txtDescription = dialogView.findViewById(R.id.txtDescription) as TextView

        val txtAmount = dialogView.findViewById(R.id.txtDescription) as TextView

        txtDescription.text = allocation!!.expenseDescription
        //txtAmount.text = allocation!!.toString().expenseAmount

        val txtImportant = dialogView.findViewById(R.id.textViewImportant) as TextView

        val txtTodo = dialogView.findViewById(R.id.textViewIdea) as TextView

        val txtIdea = dialogView.findViewById(R.id.textViewIdea) as TextView

        if (!allocation!!.important){
            txtImportant.visibility = View.GONE
        }

        if (!allocation!!.todo){
            txtTodo.visibility = View.GONE

        }

        if (!allocation!!.idea){
            txtIdea.visibility = View.GONE
        }

        val btnOK = dialogView.findViewById(R.id.btnOK) as Button

        builder.setView(dialogView).setMessage("Your Note")

        btnOK.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }

    // Receive a note from the MainActivity class
    fun sendAllocationSelected(allocationSelected: Allocation) {
        allocation = allocationSelected
    }

}