package com.lospollos.songnote.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lospollos.songnote.model.SongsModel

class AddSongFactory(private val application: Application, private val songsModel: SongsModel) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongsViewModel(application, songsModel) as T
    }

}