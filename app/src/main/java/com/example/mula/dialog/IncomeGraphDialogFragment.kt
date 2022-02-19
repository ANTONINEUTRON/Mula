package com.example.mula.dialog

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mula.R
import com.example.mula.data.model.Allocation
import com.example.mula.view_model.AllocationListViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class IncomeGraphDialogFragment(val incomeId: Int, val incomeAmount: Double) : BottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context)
        .inflate(R.layout.income_graph_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Initialize components
        val pieChart: PieChart = view.findViewById(R.id.piechart)
        val incomeTextView: TextView = view.findViewById(R.id.income_textview)
        val closeButton: Button = view.findViewById(R.id.close_button)


        //initialize viewmodel
        val factory = ViewModelProvider.AndroidViewModelFactory(context?.applicationContext as Application)
        val allocationListViewModel: AllocationListViewModel = ViewModelProvider(this,factory).get(AllocationListViewModel::class.java)

        //Fetch list of allocation
        allocationListViewModel.getAllocationById(incomeId).observe(this, Observer { listOfAllocation ->
            //check for empty list and hide somecomponents
            if(listOfAllocation.isNullOrEmpty()){
                incomeTextView.visibility = View.GONE
                pieChart.visibility = View.GONE
                view.findViewById<TextView>(R.id.no_alloc).visibility = View.VISIBLE
            }

            //prepare data
            setupPiechart(pieChart)
            //show piechart
            showPieChartData(listOfAllocation, pieChart)
            //Show Income Amount
            incomeTextView.text = "INCOME: $${incomeAmount}"
        })

        closeButton.setOnClickListener{
            this.dismiss()
        }
    }

    private fun setupPiechart(pieChart: PieChart) {
        pieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(12F)
            setEntryLabelColor(Color.BLACK)
            centerText = "Income Allocation"
            setCenterTextSize(24f)
            description.isEnabled = false

        }
        val legend: Legend = pieChart.legend
        legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(false)
            isEnabled = true
        }
    }

    private fun showPieChartData(listOfAllocation: List<Allocation>, pieChart: PieChart) {
        val listOfEntry = mutableListOf<PieEntry>()
        var sum: Float = 0f;

        //Fill list with Pie Entries
        for(alloc in listOfAllocation){
            val label:String = alloc.label
            val amount: Float = alloc.amount.toFloat()
            listOfEntry.add(PieEntry(amount, label))

            sum += amount
        }

        /**If sum of all allocation is less than provide income
         * add an Unallocated section to the pie**/
        if(sum < incomeAmount){
            listOfEntry.add(PieEntry(incomeAmount.toFloat() - sum, "Unallocated"))
        }

        val colors:List<Int> = getColors()
        val pieDataSet: PieDataSet = PieDataSet(listOfEntry, "Allocations")
        pieDataSet.setColors(colors)

        val pieData: PieData = PieData(pieDataSet)
        pieData.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }

        pieChart.apply {
            data = pieData
            invalidate()

            animateY(1100, Easing.EaseInElastic)
        }
    }

    private fun getColors(): MutableList<Int> {
        val listOfColors = mutableListOf<Int>()
        for(color in ColorTemplate.MATERIAL_COLORS){
            listOfColors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS){
            listOfColors.add(color)
        }
        return listOfColors
    }
}