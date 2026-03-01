package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ImageScaleFragment : Fragment(R.layout.fragment_image_scale) {

    private lateinit var imageView: ImageView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.iv_scalable_image)

        // 1. Load the image from the internet using Glide
        val imageUrl = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?q=80&w=2564&auto=format&fit=crop"

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        // 2. Initialize the ScaleGestureDetector
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        // 3. Attach a Touch Listener to the ImageView to intercept finger movements
        imageView.setOnTouchListener { _, event ->
            // Pass the touch event to the gesture detector
            scaleGestureDetector.onTouchEvent(event)
            true // Return true to tell Android we handled the touch
        }
    }

    // 4. Create the inner class that listens for scale (pinch) events
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // detector.scaleFactor tells us how much the user pinched/spread their fingers
            scaleFactor *= detector.scaleFactor

            // Don't let the image get too small or too huge (min 1x, max 5x)
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f))

            // Apply the scale mathematically to the view's X and Y axes
            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor

            return true
        }
    }
}
