package com.example.navigationdrawer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CustomBroadcastInputFragment : Fragment(R.layout.fragment_custom_broadcast_input) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etMessage = view.findViewById<EditText>(R.id.et_message)
        val btnSend = view.findViewById<Button>(R.id.btn_send_broadcast)

        btnSend.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                // Use Safe Args to pass the message to the next fragment
                val action = CustomBroadcastInputFragmentDirections
                    .actionNavCustomInputToCustomReceiver(message)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
