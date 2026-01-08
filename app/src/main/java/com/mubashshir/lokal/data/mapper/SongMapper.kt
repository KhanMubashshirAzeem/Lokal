package com.mubashshir.lokal.data.mapper

import com.mubashshir.lokal.data.model.Album
import com.mubashshir.lokal.data.model.AudioUrl
import com.mubashshir.lokal.data.model.Song
import com.mubashshir.lokal.data.remote.dto.AlbumDto
import com.mubashshir.lokal.data.remote.dto.DownloadUrlDto
import com.mubashshir.lokal.data.remote.dto.SongDto

fun SongDto.toDomain(): Song? {
    // We need to make sure we have the required fields to create a valid Song
    if (id == null || name == null || primaryArtists == null || url == null || image == null || downloadUrl == null) {
        return null
    }

    return Song(
        id = id,
        name = name,
        album = album?.toDomain(),
        year = year,
        duration = duration?.toLongOrNull() ?: 0L,
        label = label,
        primaryArtists = primaryArtists,
        explicitContent = explicitContent == 1,
        playCount = playCount?.toLongOrNull() ?: 0L,
        language = language,
        url = url,
        image = image.toImage(),
        audioUrl = downloadUrl.toAudioUrl()
    )
}

private fun AlbumDto.toDomain(): Album? {
    if (id == null || name == null || url == null) {
        return null
    }
    return Album(id, name, url)
}

private fun List<DownloadUrlDto>.toAudioUrl(): AudioUrl {
    val preferredUrl = firstOrNull { it.quality == "160kbps" }?.link
    val bestUrl = maxByOrNull { it.quality?.replace("kbps", "")?.toIntOrNull() ?: 0 }?.link
    return AudioUrl(preferredUrl ?: bestUrl ?: "")
}
