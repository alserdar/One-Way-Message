package alserdar89.onewaymessage.loading_and_toast

import android.content.Context
import androidx.preference.PreferenceManager

object LocalUserInfo {

    fun theUID(context: Context):String
    {

        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("theUID" , "theUID")!!
    }

    fun country(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("country" , "country")!!
    }


    fun gmail(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("gmail" , "gmail")!!
    }

    fun avatar(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("avatar" , "avatar")!!
    }

    fun userName(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("userName" , "userName")!!
    }

    fun handle(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("handle" , "handle")!!
    }

    fun handleLowerCase(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("handleLowerCase" , "handleLowerCase")!!
    }

    fun id(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("id" , "id")!!
    }

    fun littleId(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("littleId" , "littleId")!!
    }


    fun token(context: Context):String
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("token" , "token")!!
    }

    fun buildHisProfileOne(context: Context):Boolean
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getBoolean("oneBuilt" , true)
    }

    fun buildHisProfileTwo(context: Context):Boolean
    {
        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getBoolean("twoBuilt", true)
    }


    fun isTheDEviceRotedOrNot(context: Context):String
    {
        return ""
    }


    fun isTheAppInstalledFromStoreOrNot()
    {

    }


    fun lastUID(context: Context): String {

        val getInfo = PreferenceManager.getDefaultSharedPreferences(context)
        return getInfo.getString("lastUIDTalkedTo" , "lastUIDTalkedTo")!!

    }
}