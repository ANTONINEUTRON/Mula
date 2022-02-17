package com.example.mula

import android.app.Application
import androidx.room.Room
import com.example.mula.data.database.MulaDatabase

class MulacanoeApplication : Application() {
    companion object {
        lateinit var database: MulaDatabase
        private set
    }

    override fun onCreate() {
        super.onCreate()
//        database = Room
//            .databaseBuilder(
//                this,
//                MulaDatabase::class.java,
//                "allocation_database"
//            )
//            .build()
    }
}