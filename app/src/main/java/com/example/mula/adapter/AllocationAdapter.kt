package com.example.mula.adapter


import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mula.R
import com.example.mula.data.model.Allocation
import com.example.mula.dialog.NewAllocationDialog
import com.example.mula.view_model.AllocationListViewModel
import java.text.SimpleDateFormat
import java.util.*

class AllocationAdapter(private val allocationList: List<Allocation>, val allocationListViewModel: AllocationListViewModel)
    :RecyclerView.Adapter<AllocationAdapter.ListItemHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_row_content, parent, false)

        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allocationList.size
    }

    override fun onBindViewHolder(
        holder: ListItemHolder, position: Int) {

        val allocation = allocationList[position]
        holder.description.text = allocation.description

        holder.amount.text = "$${allocation.amount}"
        // Show the first 15 characters of the actual note
        holder.deductedBalance.text = "$${allocation.deductedBalance}" // REMOVED DUE TO LESS THAN 15 CHAR CRASH -> .substring(0, 15)

        val date = Date(allocation.date)
        holder.date.text = SimpleDateFormat("EEE, MMM dd YYYY").format(date)

        holder.deleteButton.setOnClickListener {
            val alert = AlertDialog.Builder(context)
            alert.apply {
                setMessage("You are about to delete this allocation")
                setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    allocationListViewModel.deleteAllocation(allocation)
                })
                setNegativeButton("Cancel", null)
                setCancelable(false)
                show()
            }
        }

        holder.editButton.setOnClickListener {
            val dialog = NewAllocationDialog(allocationListViewModel, allocation.balanceId, allocation)
            val activity = context as AppCompatActivity
            dialog.show(activity.supportFragmentManager, "Wetin do am")
        }
    }

    class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view){

        val description: TextView = view.findViewById(R.id.txtDescription)
        val amount: TextView = view.findViewById(R.id.txtAmount)
        val deductedBalance: TextView = view.findViewById(R.id.txtDeductedBalance)
        val date: TextView = view.findViewById(R.id.allocationDate)
        val deleteButton: ImageButton = view.findViewById(R.id.listDeleteButton)
        val editButton: ImageButton = view.findViewById(R.id.listEditButton)
    }

}