package com.mubashshir.lokal.data.mapper

import com.mubashshir.lokal.data.model.Artist
import com.mubashshir.lokal.data.remote.dto.ArtistDto

fun ArtistDto.toDomain(): Artist? {
    if (id == null || name == null || url == null) {
        return null
    }

    return Artist(
        id = id,
        name = name,
        url = url,
        image = image?.toImage(),
        followerCount = followerCount,
        isVerified = isVerified
    )
}
