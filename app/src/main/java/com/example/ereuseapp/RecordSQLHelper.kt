package com.example.ereuseapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ereuseapp.models.SQL_CREATE_ENTRIES

class RecordSQLHelper(context: Context): SQLiteOpenHelper(context, RecordSQLHelper.DATABASE_NAME, null, RecordSQLHelper.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        const val DATABASE_NAME = "eReuseApp.db"
        const val DATABASE_VERSION = 1
    }
}