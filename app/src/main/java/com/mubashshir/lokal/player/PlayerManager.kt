package com.mubashshir.lokal.player

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.mubashshir.lokal.data.model.Song
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import androidx.media3.common.Timeline


@Singleton
class PlayerManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState = _playerState.asStateFlow()

    private var mediaController: MediaController? = null
    private var positionUpdateJob: Job? = null

    init {
        val sessionToken = SessionToken(context, ComponentName(context, MusicPlayerService::class.java))
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener(
            {
                mediaController = controllerFuture.get()
                mediaController?.addListener(playerListener)
                updatePlayerState()
            },
            MoreExecutors.directExecutor()
        )
    }

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            updatePlayerState()
            if (isPlaying) startPositionUpdates() else stopPositionUpdates()
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            updatePlayerState()
        }

        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            updatePlayerState()
        }
    }

    private fun startPositionUpdates() {
        positionUpdateJob?.cancel()
        positionUpdateJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                updatePlayerState()
                delay(1000)
            }
        }
    }

    private fun stopPositionUpdates() {
        positionUpdateJob?.cancel()
    }

    private fun updatePlayerState() {
        val controller = mediaController ?: return
        val currentQueue = playerState.value.queue

        val newCurrentIndex = controller.currentMediaItemIndex
        val newCurrentSong = if (newCurrentIndex != -1 && newCurrentIndex < currentQueue.size) {
            currentQueue[newCurrentIndex]
        } else {
            null
        }

        _playerState.value = PlayerState(
            isPlaying = controller.isPlaying,
            currentSong = newCurrentSong,
            playbackPosition = controller.currentPosition,
            duration = controller.duration,
            queue = currentQueue,
            currentIndex = newCurrentIndex
        )
    }

    fun setQueue(songs: List<Song>, startIndex: Int) {
        val mediaItems = songs.map { it.toMediaItem() }
        mediaController?.setMediaItems(mediaItems, startIndex, 0)
        mediaController?.prepare()
        _playerState.value = _playerState.value.copy(queue = songs)
        updatePlayerState()
    }

    fun addToQueue(song: Song) {
        val currentQueue = playerState.value.queue.toMutableList()
        currentQueue.add(song)
        mediaController?.addMediaItem(song.toMediaItem())
        _playerState.value = _playerState.value.copy(queue = currentQueue)
    }

    fun removeFromQueue(song: Song) {
        val currentQueue = playerState.value.queue.toMutableList()
        val index = currentQueue.indexOf(song)
        if (index != -1) {
            currentQueue.removeAt(index)
            mediaController?.removeMediaItem(index)
            _playerState.value = _playerState.value.copy(queue = currentQueue)
        }
    }

    fun play() {
        mediaController?.play()
    }

    fun pause() {
        mediaController?.pause()
    }

    fun skipNext() {
        mediaController?.seekToNextMediaItem()
    }

    fun skipPrevious() {
        mediaController?.seekToPreviousMediaItem()
    }

    fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }

    private fun Song.toMediaItem(): MediaItem {
        val metadata = MediaMetadata.Builder()
            .setTitle(name)
            .setArtist(primaryArtists)
            .setAlbumTitle(album?.name)
            .setArtworkUri(Uri.parse(image.high ?: image.medium ?: image.low))
            .build()

        return MediaItem.Builder()
            .setUri(audioUrl.url)
            .setMediaId(id)
            .setMediaMetadata(metadata)
            .build()
    }

    fun clearAndStop() {
        mediaController?.clearMediaItems()
        mediaController?.stop()
        _playerState.value = PlayerState()
    }

    fun release() {
        mediaController?.release()
        stopPositionUpdates()
    }
}
