package com.mubashshir.lokal.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SongsResponse(
    @SerializedName("data") val data: Data,
)

data class Data(
    @SerializedName("results") val results: List<SongDto>,
)

data class SongDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("album") val album: AlbumDto?,
    @SerializedName("year") val year: String?,
    @SerializedName("releaseDate") val releaseDate: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("label") val label: String?,
    @SerializedName("primaryArtists") val primaryArtists: String?,
    @SerializedName("primaryArtistsId") val primaryArtistsId: String?,
    @SerializedName("featuredArtists") val featuredArtists: String?,
    @SerializedName("featuredArtistsId") val featuredArtistsId: String?,
    @SerializedName("explicitContent") val explicitContent: Int?,
    @SerializedName("playCount") val playCount: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("hasLyrics") val hasLyrics: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("image") val image: List<ImageDto>?,
    @SerializedName("downloadUrl") val downloadUrl: List<DownloadUrlDto>?,
)

data class AlbumDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
)

data class ImageDto(
    @SerializedName("quality") val quality: String?,
    @SerializedName("link") val link: String?,
)

data class DownloadUrlDto(
    @SerializedName("quality") val quality: String?,
    @SerializedName("link") val link: String?,
)
