package com.example.mula.dialog

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.mula.R
import com.example.mula.data.model.Income
import com.example.mula.view_model.MainActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddIncomeDialogFragment() : BottomSheetDialogFragment() {
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val factory = ViewModelProvider.AndroidViewModelFactory(context?.applicationContext as Application)
        mainActivityViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context)
            .inflate(R.layout.add_income_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val enterBalanceEditText: EditText = view.findViewById(R.id.enterBalanceId)
        val submitButton: Button = view.findViewById(R.id.submit_button)
        val closeButton: ImageButton = view.findViewById(R.id.close_button)

        submitButton.setOnClickListener() {
            var amount: Double = 0.0
            amount = validateEnteredIncome(enterBalanceEditText, amount)

            if(amount > 0.0 && amount != null) {
                val income = Income(initialAmount = amount, amount = amount)
                saveToDatabase(income)
                Toast.makeText(context,"Saved Successfully!", Toast.LENGTH_LONG).show()
                enterBalanceEditText.text.clear()
            }else{
                Toast.makeText(context,"Enter a valid Amount!", Toast.LENGTH_LONG).show()
            }

        }

        closeButton.setOnClickListener{
            this.dismiss()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun validateEnteredIncome(
        enterBalanceEditText: EditText,
        amount: Double
    ): Double {
        var amount1 = amount
        enterBalanceEditText.text.toString().also {
            if (!it.isNullOrEmpty()) {
                amount1 = it.toDouble()
            }
        }
        return amount1
    }

    private fun saveToDatabase(income: Income) {
        mainActivityViewModel.saveIncomeToDatabase(income)
    }

    companion object{
        fun newInstance(
            fm: FragmentManager,
            tag: String
        ) =
            AddIncomeDialogFragment().show(fm, tag)
    }
}