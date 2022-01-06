package com.example.mula.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allocations")
data class InitalBalance(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val initialBalance: Float?,
    @Embedded val expenses: Expenses?
)

data class Expenses(
    var expenseDescription: String?,
    var expenseAmount: Float?,
    var deductedBalance: Float? // TODO: Change to remainingBalance
)


class Allocation { //TODO: Delete after room database is competed

    var expenseDescription: String? = null
    var expenseAmount: String? = null
    var deductedBalance: Float? = null

    //THIS IS TRASH ToDO: EXPERIMENT FOR LATER
    var idea: Boolean = false
    var todo: Boolean = false
    var important: Boolean = false
}





