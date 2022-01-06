package com.example.mula.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataManager(context: Context) {

    //This is the actual database
    private val db: SQLiteDatabase

    init {
        //Create an instance of our internal
        //CustomSQLiteHelper class
        val helper = CustomSQLiteOpenHelper(context)
        //Get a writable database
        db = helper.writableDatabase
    }

    companion object{
        /*Next, we have a private const strings for
        each row/table that we need to refer to just
        inside this class*/

        private const val DB_VERSION = 1
        private const val DB_NAME = "mulacanoe"
        private const val TABLE_NAME = "allocations"

        /*
        Next, we have a const string for
        each row/table that we need to refer to both
        inside and outside this class
        */

        const val ROW_ID = "id"
        const val ROW_INITIAL_BALANCE = "initial_balance"
        const val ROW_DESCRIPTION = "description"
        const val ROW_COST = "cost"
        const val ROW_REMAINING_BALANCE = "remaining_balance"
        const val ROW_TIMESTAMP = "timestamp"
    }

    //Insert a record
    fun insert(initial_balance: String) {
        //Add all the details to the table
        val query = "INSERT INTO " + TABLE_NAME + " (" +
                ROW_INITIAL_BALANCE +
                ") " +
                "VALUES (" +
                "'" + initial_balance + "'" +
                ")";

        Log.i("com.example.mula.insert() = ", query)

        db.execSQL(query)
    }



    //This class is created when the DataManager is instantiated
    private inner class CustomSQLiteOpenHelper(context: Context)
        :SQLiteOpenHelper(context,
        DB_NAME, null,
        DB_VERSION
    ) {

        //This function only runs the first time the database is created
        override fun onCreate(db: SQLiteDatabase?) {

            //Create a table for allocation of expenses
            val newTableQueryString = ("CREATE TABLE "
                    + TABLE_NAME + " ("
                    + ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ROW_INITIAL_BALANCE
                    + " FLOAT,"
                    + ROW_DESCRIPTION
                    + " TEXT,"
                    + ROW_COST
                    + " FLOAT,"
                    + ROW_REMAINING_BALANCE
                    + " FLOAT,"
                    + ROW_TIMESTAMP
                    + " LONG ); ")

            db?.execSQL(newTableQueryString)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }

    }

    //Get all the records
    fun selectAll(): Cursor {
        return db.rawQuery("Select *" + " from " +
                TABLE_NAME, null)
    }
}