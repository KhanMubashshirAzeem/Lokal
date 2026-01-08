package com.mubashshir.lokal.data.remote

import com.mubashshir.lokal.data.remote.dto.ArtistResponse
import com.mubashshir.lokal.data.remote.dto.ArtistSongsResponse
import com.mubashshir.lokal.data.remote.dto.SongsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LokalApiService {

    @GET("search/songs")
    suspend fun searchSongs(
        @Query("query") query: String,
        @Query("limit") limit: Int
    ): SongsResponse

    @GET("songs")
    suspend fun getSongById(
        @Query("id") id: String
    ): SongsResponse // Assuming the response is similar to search

    @GET("artists")
    suspend fun getArtistById(
        @Query("id") id: String
    ): ArtistResponse

    @GET("artists/{id}/songs")
    suspend fun getArtistSongs(
        @Path("id") id: String
    ): ArtistSongsResponse
}
