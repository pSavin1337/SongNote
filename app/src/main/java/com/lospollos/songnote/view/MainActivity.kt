package com.lospollos.songnote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lospollos.songnote.R
import android.widget.TextView

import android.view.Gravity

import android.graphics.Color

import android.widget.TableLayout
import android.widget.TableRow
import androidx.lifecycle.ViewModelProvider
import com.lospollos.songnote.model.SongsModel
import com.lospollos.songnote.viewModel.AddSongFactory
import com.lospollos.songnote.viewModel.SongsViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var songsViewModel: SongsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsViewModel = ViewModelProvider(this, AddSongFactory(application,
            SongsModel(0, "", "", "")))
            .get(SongsViewModel::class.java)
        songsViewModel.operation(0)

        songsViewModel.liveData.observe(this, {
            fillTable(listOf("Название", "Автор", "Ссылка"),
                it)
        })

    }


    private fun fillTable(titles: List<String>, songs: List<SongsModel>) {
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutSongs)
        val tableRowTitles = TableRow(this)
        for (title in titles) {
            val textView = TextView(this)
            textView.textSize = 25f
            textView.text = title
            textView.setTextColor(Color.WHITE)
            textView.gravity = Gravity.CENTER
            textView.width = windowManager.defaultDisplay.width / 3
            textView.maxWidth = windowManager.defaultDisplay.width / 3
            textView.minWidth = windowManager.defaultDisplay.width / 3
            tableRowTitles.addView(textView)
        }
        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"))
        tableLayout.addView(tableRowTitles)
        for (song in songs) {
            val tableRow = TableRow(this)
            val textViewName = TextView(this)
            textViewName.text = song.name
            textViewName.height = 170
            textViewName.textSize = 16f
            textViewName.setTextColor(Color.WHITE)
            textViewName.gravity = Gravity.CENTER
            val textViewAuthor = TextView(this)
            textViewAuthor.height = 170
            textViewAuthor.textSize = 16f
            textViewAuthor.text = song.author
            textViewAuthor.setTextColor(Color.WHITE)
            textViewAuthor.gravity = Gravity.CENTER
            val textViewLink = TextView(this)
            textViewLink.text = song.link
            textViewLink.height = 170
            textViewLink.textSize = 16f
            textViewLink.setTextColor(Color.WHITE)
            textViewLink.gravity = Gravity.CENTER
            tableRow.addView(textViewName)
            tableRow.addView(textViewAuthor)
            tableRow.addView(textViewLink)
            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"))
            tableLayout.addView(tableRow)
            tableRow.setOnClickListener {
                for (i in 0 until tableLayout.childCount) {
                    val view = tableLayout.getChildAt(i)
                    (view as? TableRow)?.setBackgroundColor(Color.parseColor("#FF6200EE"))
                }
                tableRow.setBackgroundColor(Color.parseColor("#FF6200FF"))
                val intent = Intent(this, DeleteUpdateSongActivity::class.java)
                intent.putExtra("idCurrentSong", song.id)
                startActivity(intent)
            }
        }
    }

    fun addSongButtonOnClick(v : View) {
        val intent = Intent(this, AddSongActivity::class.java)
        startActivity(intent)
    }

}