package com.example.mula.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "balance")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val initialAmount: Double,
    var amount: Double,
    val date: Long = System.currentTimeMillis()
)
