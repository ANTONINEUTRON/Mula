package com.example.mula.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mula.model.InitalBalance

@Database(entities = [(InitalBalance::class)],
version = 1)
abstract class AllocationDatabase : RoomDatabase()