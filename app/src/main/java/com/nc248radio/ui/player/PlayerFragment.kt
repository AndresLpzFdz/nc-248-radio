package com.nc248radio.ui.player

import android.content.ComponentName
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.HttpDataSource
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.nc248radio.R
import com.nc248radio.databinding.FragmentPlayerBinding
import com.nc248radio.utils.extensions.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment: Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private var player: Player? = null
    private lateinit var mediaControllerFuture: ListenableFuture<MediaController>

    //Lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtons()
    }

    override fun onStart() {
        super.onStart()

        startMediaController()
    }

    override fun onStop() {
        super.onStop()

        stopMediaController()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    //Listeners

    private fun setButtons() {

        binding.playButton.setOnClickListener {
            showWaitState()
            player?.prepare()
            player?.play()
        }

        binding.pauseButton.setOnClickListener {
            player?.pause()
        }

        binding.infoButton.setOnClickListener {
            showInfoLayout()
        }

    }

    private fun setPlayerListener() {
        player?.addListener(
            object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    val message = if (error.cause is HttpDataSource.HttpDataSourceException)
                        getString(R.string.error_connection)
                    else
                        getString(R.string.error_generic)

                    showMessage(message)

                    showPauseState()
                }
            }
        )

        player?.addListener(
            object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (isPlaying) {
                        showPlayState()
                    } else {
                        player?.let {
                            if(it.playbackState == Player.STATE_READY) {
                                showPauseState()
                            } else {
                                if(it.isLoading) showWaitState()
                            }
                        }
                    }
                }
            }
        )

    }

    //Methods

    private fun startMediaController() {
        val sessionToken = SessionToken(
            requireContext(),
            ComponentName(requireContext(), PlaybackService::class.java)
        )

        mediaControllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
        mediaControllerFuture.addListener({
            player = mediaControllerFuture.get()

            checkPlayerState()
            setPlayerListener()

        }, MoreExecutors.directExecutor())
    }

    private fun stopMediaController() {
        MediaController.releaseFuture(mediaControllerFuture)
    }

    private fun checkPlayerState() {
        player?.let {
            if (it.isPlaying)
                showPlayState()
            else
                showPauseState()
        }
    }

    private fun showWaitState() {
        binding.progressLayout.isVisible = true
    }

    private fun showPlayState() {
        binding.playButton.isVisible = false
        binding.pauseButton.isVisible = true
        binding.progressLayout.isVisible = false

        showInfoText()
    }

    private fun showPauseState() {
        binding.playButton.isVisible = true
        binding.pauseButton.isVisible = false
        binding.progressLayout.isVisible = false
    }

    private fun showInfoText() {
        player?.mediaMetadata?.title?.toString()?.let {
            binding.infoText.text = it
        }
    }

    private fun showInfoLayout() {
        val modalBottomSheet = InfoFragment()
        modalBottomSheet.show(parentFragmentManager, InfoFragment.TAG)
    }

}