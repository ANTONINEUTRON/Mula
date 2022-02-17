package com.example.mula.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mula.data.model.Income

@Dao
interface IncomeDao {
    @Query("SELECT * FROM balance")
    fun fetchAllIncome(): LiveData<List<Income>>

    @Insert(entity = Income::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveIncome(income: Income)

    @Query("SELECT * FROM balance WHERE id = :id")
    fun getIncomeById(id: Int): LiveData<Income>

    @Query("UPDATE balance SET amount = amount + :amount WHERE id = :id")
    fun incrementIncome(id: Int, amount: Double)
}