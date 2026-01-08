package com.mubashshir.lokal.data.model

data class Artist(
    val id: String,
    val name: String,
    val url: String,
    val image: Image?,
    val followerCount: String?,
    val isVerified: Boolean?
)
