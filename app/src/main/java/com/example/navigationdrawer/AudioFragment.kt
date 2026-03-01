package com.example.navigationdrawer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class AudioFragment : Fragment(R.layout.fragment_audio) {

    // MediaPlayer can be null when it's released, so we make it nullable (?)
    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPlay = view.findViewById<Button>(R.id.btn_play)
        val btnPause = view.findViewById<Button>(R.id.btn_pause)
        val btnStop = view.findViewById<Button>(R.id.btn_stop)

        // 1. Initialize MediaPlayer (This automatically prepares it for playback)
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sample_audio)

        btnPlay.setOnClickListener {
            // Only start if it's not already playing
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
            }
        }

        btnPause.setOnClickListener {
            // Pause the audio
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        btnStop.setOnClickListener {
            // Stop playback. Once stopped, it must be prepared again before playing.
            mediaPlayer?.stop()
            mediaPlayer?.prepare()
        }
    }

    // 2. CRUCIAL: Release the MediaPlayer when the fragment is destroyed
    override fun onDestroyView() {
        super.onDestroyView()

        // Stop playing immediately if the user navigates away
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }

        // Release the resources back to the Android system
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
