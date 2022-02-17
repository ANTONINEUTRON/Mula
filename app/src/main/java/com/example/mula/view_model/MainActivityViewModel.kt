package com.example.mula.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mula.data.database.MulaDatabase
import com.example.mula.data.model.Income


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val database: MulaDatabase = MulaDatabase.getDatabase(getApplication())

    fun getAllIncome(): LiveData<List<Income>>{
        return database.incomeDao().fetchAllIncome()
    }

    fun saveIncomeToDatabase(income: Income) {

        database.incomeDao().saveIncome(income)
    }
}