package com.example.navigationdrawer

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoFragment : Fragment(R.layout.fragment_video) {

    private lateinit var videoView: VideoView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoView = view.findViewById(R.id.video_view)

        // 1. Setup the MediaController (Play/Pause/Seek bar)
        val mediaController = MediaController(requireContext())
        // Tell the controller where to attach itself on the screen
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // 2. Build the exact "address" (URI) to your raw video file
        val packageName = requireActivity().packageName
        val videoUri = Uri.parse("android.resource://$packageName/${R.raw.sample_video}")

        // 3. Give the URI to the VideoView
        videoView.setVideoURI(videoUri)

        // 4. (Optional) Auto-start the video once it's prepared and ready
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }
}
