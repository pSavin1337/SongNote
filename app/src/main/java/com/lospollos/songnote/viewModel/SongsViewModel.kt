package com.lospollos.songnote.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lospollos.songnote.model.SongsModel
import com.lospollos.songnote.model.SongsStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SongsViewModel(application: Application, var songsModel: SongsModel) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<SongsModel>>()
    private val songsStorage = SongsStorage(application)

    fun operation(operation : Int) {
        when(operation) {
            0 -> getSongs()
            1 -> insert()
            2 -> delete()
            3 -> update()
            4 -> getSongForForm()
        }
    }

    private fun getSongs() {
        liveData.value = songsStorage.getSongs()
    }

    private fun insert() {
        songsStorage.addSong(songsModel)
    }

    private fun getSongForForm() {
        liveData.value = songsStorage.getSongById(songsModel.id)
    }

    private fun update() {
        songsStorage.updateSong(songsModel.id, songsModel)
    }

    private fun delete() {
        songsStorage.deleteSong(songsModel.id)
    }

}