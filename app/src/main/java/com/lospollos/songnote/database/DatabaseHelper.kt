package com.lospollos.songnote.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS songs (\n" +
                    "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                    "    name character(100) NOT NULL,\n" +
                    "    author character(100) NOT NULL,\n" +
                    "\t  link character(100));\n"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME)
        onCreate(db)
    }

    companion object {
        private val DATABASE_NAME = "songs.db" // название бд
        private val DATABASE_VERSION = 1 // версия базы данных
    }
}