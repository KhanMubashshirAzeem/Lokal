package com.mubashshir.lokal.data.model

import java.util.concurrent.TimeUnit

data class Song(
    val id: String,
    val name: String,
    val album: Album?,
    val year: String?,
    val duration: Long,
    val label: String?,
    val primaryArtists: String,
    val explicitContent: Boolean,
    val playCount: Long,
    val language: String?,
    val url: String,
    val image: Image,
    val audioUrl: AudioUrl
) {
    val durationLabel: String
        get() {
            val minutes = TimeUnit.SECONDS.toMinutes(duration)
            val seconds = duration - TimeUnit.MINUTES.toSeconds(minutes)
            return String.format("%02d:%02d", minutes, seconds)
        }
}
