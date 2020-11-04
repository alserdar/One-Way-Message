package alserdar89.onewaymessage.loading_and_toast

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object ReturnDateAndTime {

    fun returnTheDate(): String {

        val date = Date()
        //  Date newDate = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
        @SuppressLint("SimpleDateFormat") val dt = SimpleDateFormat("dd-MMM-yyyy")
        return dt.format(date)
    }

    fun returnTheTime(): String {

        /*
          Date date = new Date();
        Date newTime = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        return dt.format(newTime);
         */
        val date = Date()
        @SuppressLint("SimpleDateFormat") val timeFormatter = SimpleDateFormat("h:mm a")
        return timeFormatter.format(date)
    }

}