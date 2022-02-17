package com.example.mula.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mula.data.dao.AllocationDao
import com.example.mula.data.dao.IncomeDao
import com.example.mula.data.model.Allocation
import com.example.mula.data.model.Income

@Database(entities = [Allocation::class, Income::class], version = 1, exportSchema = false)
abstract class MulaDatabase : RoomDatabase(){

    companion object{
        var INSTANCE: MulaDatabase? = null

        fun getDatabase(context: Context): MulaDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, MulaDatabase::class.java, "d_database")
                    .allowMainThreadQueries().build()
            }

            return INSTANCE!!
        }
    }

    abstract fun allocationDao(): AllocationDao
    abstract fun incomeDao(): IncomeDao
}