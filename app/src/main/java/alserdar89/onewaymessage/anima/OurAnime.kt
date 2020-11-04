package alserdar89.onewaymessage.anima

import alserdar89.onewaymessage.R
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner

object OurAnime {

    fun shikshakshock(context:Context , view: View)
    {
        var animation: Animation?= null
        animation = AnimationUtils.loadAnimation(context,
            R.anim.shake
        )
        animation.duration = 2000
        view.startAnimation(animation)

    }


    fun shikshakshockForNickName(context:Context , view: EditText)
    {
        var animation: Animation?= null
        animation = AnimationUtils.loadAnimation(context,
            R.anim.shake
        )
        animation.duration = 2000
        view.startAnimation(animation)

    }

    fun shikshakshockForSpinner(context:Context , view: Spinner)
    {
        var animation: Animation?= null
        animation = AnimationUtils.loadAnimation(context,
            R.anim.shake
        )
        animation.duration = 2000
        view.startAnimation(animation)

    }

    fun fadeIn(context:Context , view: ListView)
    {
        var animation: Animation?= null
        animation = AnimationUtils.loadAnimation(context,
            R.anim.fade_in
        )
        animation.duration = 2000
        view.startAnimation(animation)

    }


    fun startRotation(start: Float, end: Float, image: ImageView) {
        // Calculating center point
        val centerX: Float = image.width / 2.0f
        val centerY: Float = image.height / 2.0f
        //Log.d(TAG, "centerX="+centerX+", centerY="+centerY);
        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        //final Rotate3dAnimation rotation =new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        //Z axis is scaled to 0
        val rotation = AnimateThe3D(
            start,
            end,
            centerX,
            centerY,
            0f,
            true
        )
        rotation.duration = 2000
        rotation.fillAfter = true
        //rotation.setInterpolator(new AccelerateInterpolator());
        //Uniform rotation
        rotation.interpolator = LinearInterpolator()
        image.startAnimation(rotation)
    }

}