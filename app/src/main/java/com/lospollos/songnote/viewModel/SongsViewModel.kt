package com.lospollos.songnote.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lospollos.songnote.model.SongsModel
import com.lospollos.songnote.model.SongsStorage

class SongsViewModel(application: Application, var songsModel: SongsModel) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<SongsModel>>()
    val songsStorage = SongsStorage(application)

    init {
        if(songsModel.toAdd)
            insert()
        else
            getSongs()
    }

    private fun getSongs() {
        liveData.value = songsStorage.getSongs()
    }

    private fun insert() {
        songsStorage.addSong(songsModel)
    }

}