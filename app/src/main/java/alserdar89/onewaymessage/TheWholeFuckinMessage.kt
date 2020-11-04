package alserdar89.onewaymessage

import alserdar89.onewaymessage.loading_and_toast.LoadPics
import alserdar89.onewaymessage.login.SingleSignIn
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class TheWholeFuckinMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_whole_fuckin_message)

        val name:TextView = findViewById(R.id.senderNameInWhole)
        val message:TextView = findViewById(R.id.theWholeMessage)
        val pico:ImageView = findViewById(R.id.senderPicInWhole)

        val theUID = intent.extras!!.getString("theUID")
        val theName = intent.extras!!.getString("theName")
        val theMessage = intent.extras!!.getString("theMessage")

        name.text = theName.toString()
        message.text = theMessage.toString()

        LoadPics.loadPics(this , pico , theUID!!)



    }

    override fun onBackPressed() {
        val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
        val intent = Intent(this, TheHome::class.java)
        this.startActivity(intent, bundle)
        finish()

    }
}