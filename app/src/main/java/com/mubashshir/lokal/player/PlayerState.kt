package com.mubashshir.lokal.player

import com.mubashshir.lokal.data.model.Song

data class PlayerState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val playbackPosition: Long = 0,
    val duration: Long = 0,
    val queue: List<Song> = emptyList(),
    val currentIndex: Int = -1
)
