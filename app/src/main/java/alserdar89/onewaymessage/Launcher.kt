package alserdar89.onewaymessage

import alserdar89.onewaymessage.loading_and_toast.LocalUserInfo
import alserdar89.onewaymessage.loading_and_toast.OurToast
import alserdar89.onewaymessage.login.SingleSignIn
import alserdar89.onewaymessage.login.availableNetwork
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Launcher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher)


        Handler().postDelayed({
        },2300)

        if (availableNetwork(this))
        {
            Handler().postDelayed(
                {

                    val yeah:Boolean =  FirebaseAuth.getInstance().currentUser != null
                    val haveUID:Boolean = LocalUserInfo.theUID(this) != "theUID" ||
                            LocalUserInfo.theUID(this) != ""

                    if (yeah)
                    {
                        if (haveUID)
                        {
                            makeMeOnline()
                            val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
                            val intent = Intent(this, TheHome::class.java)
                            this.startActivity(intent, bundle)
                            finish()

                        }else
                        {
                            val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
                            val intent = Intent(this, SingleSignIn::class.java)
                            this.startActivity(intent, bundle)
                            finish()
                        }


                    }else
                    {
                        val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
                        val intent = Intent(this, SingleSignIn::class.java)
                        this.startActivity(intent, bundle)
                        finish()
                    }

                },2500
            )
        }else
        {

        }


    }


    fun makeMeOnline() {
        val db = FirebaseFirestore.getInstance()
        val ref = db.collection("UserInformation").document(LocalUserInfo.theUID(baseContext))
        ref.update(
            mapOf(
                "online" to true

            )
        ).addOnSuccessListener { void ->
            OurToast.myToast(this, "Enjoy , You are Online")
        }

    }
}