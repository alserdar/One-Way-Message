package alserdar89.onewaymessage.login

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity



    fun availableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    fun getWifiLevel(context: Context): Int {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val linkSpeed = wifiManager.connectionInfo.rssi
        return WifiManager.calculateSignalLevel(linkSpeed, 5)
    }