package com.android.system.androidsystemx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

interface BatteryStatusCallback {
    fun onBatteryStatusChanged()
}

class ChargingReceiver(private val batteryCallback: BatteryStatusCallback) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging =
                status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

            if (isCharging) {
                batteryCallback.onBatteryStatusChanged()
            } else {
                Log.d("check1", "Not Charging")
            }
        }
    }
}

