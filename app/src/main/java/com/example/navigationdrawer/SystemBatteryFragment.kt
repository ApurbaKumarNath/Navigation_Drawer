package com.example.navigationdrawer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class SystemBatteryFragment : Fragment(R.layout.fragment_system_battery) {

    private lateinit var tvBatteryLevel: TextView

    // 1. Create the BroadcastReceiver
    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // This block runs whenever the battery level changes (or when first registered)
            if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
                // The system provides the current level and the maximum scale (usually 100)
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

                if (level != -1 && scale != -1) {
                    val batteryPct = (level * 100) / scale.toFloat()
                    tvBatteryLevel.text = "Battery Level: ${batteryPct.toInt()}%"
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBatteryLevel = view.findViewById(R.id.tv_battery_level)
    }

    // 2. Register the receiver when the fragment becomes visible
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        // We use requireActivity() because the fragment relies on its host activity for system services
        requireActivity().registerReceiver(batteryReceiver, intentFilter)
    }

    // 3. Unregister the receiver when the fragment is no longer visible
    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(batteryReceiver)
    }
}
