package com.example.mula.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mula.data.database.MulaDatabase
import com.example.mula.data.model.Allocation
import com.example.mula.data.model.Income

class AllocationListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MulaDatabase.getDatabase(application)

    fun getIncomeById(id: Int): LiveData<Income>{
        return database.incomeDao().getIncomeById(id)
    }

    fun getAllocationById(id: Int): LiveData<List<Allocation>>{
        return database.allocationDao().getAllocations(id)
    }

    fun setNewAllocation(newAllocation: Allocation){
        //insert allocation
        database.allocationDao().insertAllocation(newAllocation)
    }

    fun updateNewIncome(income: Income) {
        database.incomeDao().saveIncome(income)
    }

    fun deleteAllocation(allocation: Allocation) {
        database.allocationDao().deleteAllocation(allocation)

        //Increment the income by the amount of removed allocation
        database.incomeDao().incrementIncome(allocation.balanceId, allocation.amount)
    }
}