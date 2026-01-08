package com.mubashshir.lokal.player.viewmodel

import com.mubashshir.lokal.data.model.Song

data class PlayerUiState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val position: Long = 0,
    val duration: Long = 0,
    val queue: List<Song> = emptyList(),
    val isBuffering: Boolean = false,
    val hasNext: Boolean = false,
    val hasPrevious: Boolean = false
)
