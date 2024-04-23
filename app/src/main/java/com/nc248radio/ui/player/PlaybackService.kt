package com.nc248radio.ui.player

import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.nc248radio.R
import com.nc248radio.utils.UriUtils

class PlaybackService : MediaSessionService() {

    companion object {
        const val RADIO_URL = "https://stream-172.zeno.fm/jlzdvi5d0hpvv"
    }

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.Builder()
            .setUri(RADIO_URL)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setArtworkUri(UriUtils.getURIForResource(R.drawable.logo))
                    .build()
            ).build()
        player.setMediaItem(mediaItem)

        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        mediaSession?.let {
            val player = it.player
            if (player.playWhenReady) player.pause()
        }

        stopSelf()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }

        super.onDestroy()
    }

}