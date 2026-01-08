package com.mubashshir.lokal.data.repository

import com.mubashshir.lokal.core.UiState
import com.mubashshir.lokal.data.model.Artist
import com.mubashshir.lokal.data.model.Song
import kotlinx.coroutines.flow.Flow

interface LokalRepository {
    fun searchSongs(query: String, limit: Int = 20): Flow<UiState<List<Song>>>
    fun getSongById(id: String): Flow<UiState<Song>>
    fun getArtistById(id: String): Flow<UiState<Artist>>
    fun getArtistSongs(id: String): Flow<UiState<List<Song>>>
}
