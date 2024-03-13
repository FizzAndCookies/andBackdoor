package com.android.system.androidsystemx

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {

    //request permissions
    fun requestAllPermissions(context: Context){
        val requestAllPermissions = 100
        val permissionsToRequest = arrayOf(
            Manifest.permission.READ_CONTACTS, //contacts permission
            Manifest.permission.READ_SMS, //sms permission
            Manifest.permission.SEND_SMS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG //read call log permission,


        )
        val permissionsNotGranted = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsNotGranted.isNotEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, permissionsNotGranted, requestAllPermissions)
        }
    }

    //ask permission to ignore battery optimization
    @SuppressLint("BatteryLife")
    @RequiresApi(Build.VERSION_CODES.M)
    fun requestIgnoreBatteryOptimizations(context: Context) {
        val packageName = context.packageName
        val intent = Intent().apply {
            action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            data = android.net.Uri.parse("package:$packageName")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
     fun requestUnrestrictedDataPermission(context:Context) {
        // Intent to open the data usage settings screen

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val intent = Intent(Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS)
            context.startActivity(intent)
        }
    }

    //notification menu
    fun goToNotificationSettings(context: Context) {
        val intent: Intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else {
            intent = Intent("android.settings.APP_NOTIFICATION_SETTINGS")
            intent.putExtra("app_package", context.packageName)
            intent.putExtra("app_uid", context.applicationInfo.uid)
        }

        context.startActivity(intent)
    }
}
