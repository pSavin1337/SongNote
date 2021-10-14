package com.lospollos.songnote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.lospollos.songnote.R
import com.lospollos.songnote.model.SongsModel
import com.lospollos.songnote.viewModel.AddSongFactory
import com.lospollos.songnote.viewModel.SongsViewModel

class DeleteUpdateSongActivity : AppCompatActivity() {

    private lateinit var songsViewModel: SongsViewModel
    private var currentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        currentId = intent.extras?.getInt("idCurrentSong").toString().toInt()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_update_song)
        val nameForm = findViewById<EditText>(R.id.nameForm)
        val authorForm = findViewById<EditText>(R.id.authorForm)
        val linkForm = findViewById<EditText>(R.id.linkForm)

        songsViewModel = ViewModelProvider(this, AddSongFactory(application,
            SongsModel(currentId, "", "", "")))
            .get(SongsViewModel::class.java)
        songsViewModel.operation(4)

        songsViewModel.liveData.observe(this, {
            nameForm.text = SpannableStringBuilder(it[0].name)
            authorForm.text = SpannableStringBuilder(it[0].author)
            linkForm.text = SpannableStringBuilder(it[0].link)
        })

    }

    fun onClickButtonBack(v : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickButtonUpdate(v : View) {

        val nameForm = findViewById<EditText>(R.id.nameForm)
        val authorForm = findViewById<EditText>(R.id.authorForm)
        val linkForm = findViewById<EditText>(R.id.linkForm)

        val songsModel = SongsModel(currentId,
            nameForm.text.toString(),
            authorForm.text.toString(),
            linkForm.text.toString()
        )
        songsViewModel.songsModel = songsModel
        songsViewModel.operation(3)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    fun onClickButtonDelete(v : View) {

        val songsModel = SongsModel(currentId, "", "", "")
        songsViewModel.songsModel = songsModel
        songsViewModel.operation(2)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

}