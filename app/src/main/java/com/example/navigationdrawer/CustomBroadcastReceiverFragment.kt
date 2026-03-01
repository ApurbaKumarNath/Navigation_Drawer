package com.example.navigationdrawer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class CustomBroadcastReceiverFragment : Fragment(R.layout.fragment_custom_broadcast_receiver) {

    // Define a unique action string for your custom broadcast
    private val MY_CUSTOM_ACTION = "com.example.navigationdrawer.MY_CUSTOM_MESSAGE"

    private lateinit var tvReceivedMessage: TextView

    // Retrieve the arguments passed from the previous fragment
    private val args: CustomBroadcastReceiverFragmentArgs by navArgs()

    // 1. Create the Receiver
    private val customReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MY_CUSTOM_ACTION) {
                // Extract the string we attached to the broadcast intent
                val receivedText = intent.getStringExtra("EXTRA_MESSAGE")
                tvReceivedMessage.text = receivedText
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvReceivedMessage = view.findViewById(R.id.tv_received_message)
    }

    override fun onResume() {
        super.onResume()

        // 2. Register the receiver securely using ContextCompat
        val intentFilter = IntentFilter(MY_CUSTOM_ACTION)

        ContextCompat.registerReceiver(
            requireContext(),
            customReceiver,
            intentFilter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        // 3. Fire the broadcast
        val messageToBroadcast = args.messageToBroadcast
        val broadcastIntent = Intent(MY_CUSTOM_ACTION)
        broadcastIntent.putExtra("EXTRA_MESSAGE", messageToBroadcast)

        // 4. Explicitly set the target package to satisfy Android 14 security requirements
        broadcastIntent.setPackage(requireContext().packageName)

        requireActivity().sendBroadcast(broadcastIntent)
    }

    override fun onPause() {
        super.onPause()
        // 5. Unregister to prevent memory leaks
        requireActivity().unregisterReceiver(customReceiver)
    }
}
