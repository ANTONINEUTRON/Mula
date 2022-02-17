package com.example.mula.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mula.R
import com.example.mula.activity.AllocationListActivity
import com.example.mula.data.model.Income
import java.text.SimpleDateFormat
import java.util.*

class IncomeListAdapter(val listOfIncome: MutableList<Income>): RecyclerView.Adapter<IncomeListAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.income_list_layout,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val income = listOfIncome[position]
        holder.balanceAmount.text = "$ ${income.initialAmount}"
        holder.date.text = SimpleDateFormat("EEE, MMM dd YYYY").format(income.date)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, AllocationListActivity::class.java)
            intent.putExtra("BALANCE_ID", income.id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfIncome.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date_textview)
        val balanceAmount: TextView = itemView.findViewById(R.id.balance_textview)
    }
}