package com.lospollos.songnote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.lospollos.songnote.R
import com.lospollos.songnote.model.SongsModel
import com.lospollos.songnote.viewModel.AddSongFactory
import com.lospollos.songnote.viewModel.SongsViewModel

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)
    }

    fun onClickButtonAddSong(v : View) {

        val nameForm = findViewById<EditText>(R.id.nameForm)
        val authorForm = findViewById<EditText>(R.id.authorForm)
        val linkForm = findViewById<EditText>(R.id.linkForm)

        val songsModel = SongsModel(nameForm.text.toString(),
            authorForm.text.toString(),
            linkForm.text.toString(),
            true
        )
        val songsViewModel = ViewModelProvider(this, AddSongFactory(application, songsModel))
            .get(SongsViewModel::class.java)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

}