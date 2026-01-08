package com.mubashshir.lokal.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.mubashshir.lokal.player.MusicPlayerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object PlayerModule {

    @Provides
    @ServiceScoped
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @ServiceScoped
    fun provideMediaSession(exoPlayer: ExoPlayer, @ApplicationContext context: Context): MediaSession {
        return MediaSession.Builder(context, exoPlayer).build()
    }
}
