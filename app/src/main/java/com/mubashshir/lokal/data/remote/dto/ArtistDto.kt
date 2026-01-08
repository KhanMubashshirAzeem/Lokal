package com.mubashshir.lokal.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("image") val image: List<ImageDto>?,
    @SerializedName("follower_count") val followerCount: String?,
    @SerializedName("is_verified") val isVerified: Boolean?
)

data class ArtistResponse(
    @SerializedName("data") val data: ArtistDto,
)

data class ArtistSongsResponse(
    @SerializedName("data") val data: ArtistSongsData,
)

data class ArtistSongsData(
    @SerializedName("songs") val songs: List<SongDto>
)
