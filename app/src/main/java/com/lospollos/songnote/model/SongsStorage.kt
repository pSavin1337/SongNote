package com.lospollos.songnote.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SongsStorage(context: Context) {

    private var context: Context
    private var db: SQLiteDatabase

    init {
        val dbHelper = DatabaseHelper(context)
        this.context = context
        try {
            this.db = dbHelper.writableDatabase
        }
        catch (ex : SQLiteException){
            this.db = dbHelper.readableDatabase
        }
    }

    @SuppressLint("Range")
    fun getSongs() : List<SongsModel> {
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        val list: MutableList<SongsModel> = ArrayList()
        if (!cursor.moveToFirst()) {
            return list
        }
        do {
            val songsModel = SongsModel(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("author")),
                cursor.getString(cursor.getColumnIndex("link"))
            )
            list.add(songsModel)
            cursor.moveToNext()
        } while (!cursor.isAfterLast)
        cursor.close()
        return list
    }

    @SuppressLint("Range")
    fun getSongById(id : Int) : List<SongsModel> {
        val cursor = db.query(TABLE_NAME, null, "id = $id", null, null, null, null)

        val list: MutableList<SongsModel> = ArrayList()
        cursor.moveToFirst()
        val songsModel = SongsModel(
            cursor.getInt(cursor.getColumnIndex("id")),
            cursor.getString(cursor.getColumnIndex("name")),
            cursor.getString(cursor.getColumnIndex("author")),
            cursor.getString(cursor.getColumnIndex("link"))
        )
        list.add(songsModel)
        cursor.close()
        return list
    }

    fun addSong(songsModel: SongsModel) {
        CoroutineScope(Dispatchers.IO).async {
            val contentValues = ContentValues()
            contentValues.put("name", songsModel.name)
            contentValues.put("author", songsModel.author)
            contentValues.put("link", songsModel.link)
            db.insert(TABLE_NAME, null, contentValues)
        }
    }

    fun updateSong(id: Int, newSongsModel: SongsModel) {
        CoroutineScope(Dispatchers.IO).async {
            val contentValues = ContentValues()
            contentValues.put("name", newSongsModel.name)
            contentValues.put("author", newSongsModel.author)
            contentValues.put("link", newSongsModel.link)
            val where = "id = $id"
            db.update(TABLE_NAME, contentValues, where, null)
        }
    }

    fun deleteSong(id : Int) {
        CoroutineScope(Dispatchers.IO).async {
            val where = "id = $id"
            db.delete(TABLE_NAME, where, null)
        }
    }

    companion object {
        private const val TABLE_NAME = "songs" // название бд
    }

}