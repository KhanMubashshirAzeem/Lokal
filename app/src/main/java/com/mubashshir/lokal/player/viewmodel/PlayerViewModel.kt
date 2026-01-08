package com.mubashshir.lokal.player.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubashshir.lokal.data.model.Song
import com.mubashshir.lokal.player.PlayerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerManager: PlayerManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState = _uiState.asStateFlow()

    init {
        playerManager.playerState
            .onEach { playerState ->
                _uiState.value = PlayerUiState(
                    currentSong = playerState.currentSong,
                    isPlaying = playerState.isPlaying,
                    position = playerState.playbackPosition,
                    duration = playerState.duration,
                    queue = playerState.queue,
                    isBuffering = false, // TODO: Get buffering state from PlayerState
                    hasNext = playerState.currentIndex < playerState.queue.size - 1,
                    hasPrevious = playerState.currentIndex > 0
                )
            }
            .launchIn(viewModelScope)
    }

    fun play() {
        playerManager.play()
    }

    fun pause() {
        playerManager.pause()
    }

    fun togglePlayPause() {
        if (uiState.value.isPlaying) {
            pause()
        } else {
            play()
        }
    }

    fun seekTo(position: Long) {
        playerManager.seekTo(position)
    }

    fun playNext() {
        playerManager.skipNext()
    }

    fun playPrevious() {
        playerManager.skipPrevious()
    }

    fun playFromQueue(index: Int) {
        val song = uiState.value.queue[index]
        playerManager.setQueue(uiState.value.queue, index)
        play()
    }

    fun addToQueue(song: Song) {
        playerManager.addToQueue(song)
    }

    fun removeFromQueue(song: Song) {
        playerManager.removeFromQueue(song)
    }

    fun setQueue(songs: List<Song>, startIndex: Int) {
        playerManager.setQueue(songs, startIndex)
    }
}
