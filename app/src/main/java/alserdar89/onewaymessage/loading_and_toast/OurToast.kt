package alserdar89.onewaymessage.loading_and_toast

import alserdar89.onewaymessage.R
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

object OurToast {


    fun myToast(context: Context?, msg: String) {


        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        val toastView = toast.view //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
        val toastMessage = toastView.findViewById<TextView>(android.R.id.message)
        toastMessage.setTextColor(Color.WHITE)
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0 ,0,
            R.mipmap.one_way_message, 0)
        toastMessage.gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
        toastMessage.compoundDrawablePadding = 15
        toastView.setBackgroundColor(Color.TRANSPARENT)
       // toastView.setBackgroundResource(R.drawable.design_one)
      //  toastView.setPadding(15)
        toast.show()

    }

    fun myToastPic(context: Context, msg: String, icon: Int) {

        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        val toastView = toast.view //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
        val toastMessage = toastView.findViewById<TextView>(android.R.id.message)
        toastMessage.setTextColor(Color.WHITE)
       // toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_slinker, 0, 0, 0)
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
        toastMessage.gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
        toastMessage.compoundDrawablePadding = 15
        toastView.setBackgroundColor(Color.WHITE)
      //  toastView.setBackgroundResource(R.drawable.design_four)
        toast.show()

    }
}