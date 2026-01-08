package com.mubashshir.lokal.data.repository

import com.mubashshir.lokal.core.UiState
import com.mubashshir.lokal.data.mapper.toDomain
import com.mubashshir.lokal.data.model.Artist
import com.mubashshir.lokal.data.model.Song
import com.mubashshir.lokal.data.remote.LokalApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LokalRepositoryImpl @Inject constructor(
    private val apiService: LokalApiService
) : LokalRepository {

    override fun searchSongs(query: String, limit: Int): Flow<UiState<List<Song>>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.searchSongs(query, limit)
            val songs = response.data.results.mapNotNull { it.toDomain() }
            emit(UiState.Success(songs))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }

    override fun getSongById(id: String): Flow<UiState<Song>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.getSongById(id)
            val song = response.data.results.firstOrNull()?.toDomain()
            if (song != null) {
                emit(UiState.Success(song))
            } else {
                emit(UiState.Error("Song not found"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }

    override fun getArtistById(id: String): Flow<UiState<Artist>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.getArtistById(id)
            val artist = response.data.toDomain()
            if (artist != null) {
                emit(UiState.Success(artist))
            } else {
                emit(UiState.Error("Artist not found"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }

    override fun getArtistSongs(id: String): Flow<UiState<List<Song>>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.getArtistSongs(id)
            val songs = response.data.songs.mapNotNull { it.toDomain() }
            emit(UiState.Success(songs))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }
}
