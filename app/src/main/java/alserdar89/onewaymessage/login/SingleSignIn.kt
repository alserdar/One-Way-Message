package alserdar89.onewaymessage.login

import alserdar89.onewaymessage.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.common.SignInButton

class SingleSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_sign_in)

        val sign: SignInButton = findViewById(R.id.signHere)

        sign.setOnClickListener(View.OnClickListener {
            val i = Intent(this , DetectCountry::class.java)
            startActivity(i)
            finish()
        })

    }
}