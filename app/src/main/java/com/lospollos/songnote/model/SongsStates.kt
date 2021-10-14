package com.lospollos.songnote.model

sealed class SongsStates {

    class DefaultState: SongsStates()
    class AddState: SongsStates()
    class UpdateState: SongsStates()
    class DeleteState: SongsStates()

}