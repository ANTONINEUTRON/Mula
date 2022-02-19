package com.example.mula.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "allocation")
data class Allocation(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var label: String = "",
    var amount: Double = 0.0,
    var deductedBalance: Double = 0.0,
    var date: Long = System.currentTimeMillis(),
    var balanceId: Int = -1, //FOREIGN KEY: Primary Key of Balance Entity it is attached to
)