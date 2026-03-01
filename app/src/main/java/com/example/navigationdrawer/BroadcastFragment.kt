package com.example.navigationdrawer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class BroadcastFragment : Fragment(R.layout.fragment_broadcast) {

    // onViewCreated is called immediately after the view is created in onCreateView.
    // It's the safest place to initialize your views in a Fragment.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(R.id.spinner_broadcast_type)
        val btnProceed = view.findViewById<Button>(R.id.btn_proceed)

        btnProceed.setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition

            if (selectedPosition == 0) {
                // "Custom broadcast receiver" was selected
                findNavController().navigate(R.id.action_nav_broadcast_to_customInput)
            } else {
                // "System battery notification receiver" was selected
                findNavController().navigate(R.id.action_nav_broadcast_to_systemBattery)
            }
        }
    }
}
