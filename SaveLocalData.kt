package com.android.system.androidsystemx

import android.content.Context

class SaveLocalData {

    //save app data for icon changing
    fun saveAppData(context: Context, data: String) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("activity", data)
        editor.apply()
    }

    //retrieve app data - returns data
    fun appData(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("activity", null)
    }

    //save app data for icon changing
    fun saveIsConnected(context: Context, data: String) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("isConnected", data)
        editor.apply()
    }

    //retrieve is connected value
    fun isConnected(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("isConnected", null)
    }
}
