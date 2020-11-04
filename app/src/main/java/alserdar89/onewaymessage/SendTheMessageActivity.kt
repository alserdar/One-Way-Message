package alserdar89.onewaymessage

import alserdar89.onewaymessage.anima.OurAnime
import alserdar89.onewaymessage.loading_and_toast.LocalUserInfo
import alserdar89.onewaymessage.loading_and_toast.ReturnDateAndTime
import alserdar89.onewaymessage.models.NotificationModel
import alserdar89.onewaymessage.models.UserModel
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class SendTheMessageActivity : AppCompatActivity() {

    lateinit var theMessageET: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_the_message)
        theMessageET  = findViewById(R.id.theMessage)
        val spreadIt : Button = findViewById(R.id.spreadTheWord)



        spreadIt.setOnClickListener(View.OnClickListener {




            if (theMessageET.text.toString().equals(""))
            {
                OurAnime.shikshakshockForNickName(this , theMessageET)

            }else
            {
                doTheSharmRandomly()

            }


        })
    }


    fun pushTheNotifications(context: Context, theMessage:String?, theTargetUID: String?, theTargetToken:String)
    {
        val dbStore: FirebaseFirestore = FirebaseFirestore.getInstance()
        dbStore.collection("UserInformation").document(LocalUserInfo.theUID(context))
            .get().addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot.exists())
                {
                    val user = documentSnapshot.toObject(UserModel::class.java)!!

                    val db = FirebaseDatabase.getInstance()
                    val docForMe = db.getReference("notifications").child(theTargetUID!!).push()

                    docForMe.setValue(
                        NotificationModel(
                            theMessage, user.userName,
                            user.theUID, user.littleId,
                            "images/" + user.theUID,
                            theTargetToken
                            , ReturnDateAndTime.returnTheDate(), ReturnDateAndTime.returnTheTime()
                        )
                    )
                        .addOnCompleteListener(OnCompleteListener {

                            gotoChooseUploadScreen(this)
                        })
                }

            }
    }

    fun doTheSharmRandomly()
    {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val query = db.collection("UserInformation")
        // here we wanna add the online one

        query.get().addOnSuccessListener { querySnapshot: QuerySnapshot? ->

            val users = querySnapshot!!.toObjects(UserModel::class.java)

            val howManyTargets = users.size-1

            for (i in 0 .. 0)
            {
                val theTarget = users.get(randomUsers(howManyTargets))

                if (theTarget.theUID != LocalUserInfo.theUID(this))
                {

                    /*   LoadPics.loadPics(requireContext() , binding.getTheSharmed , theTarget.theUID!!)

                       binding.hisName.text = theTarget.userName

                       binding.hisCountry.text = theTarget.country

                       binding.theMessage.hint = "Send another Message"
   */
                    // OurAnime.startRotation(360f , 2.0f , binding.getTheSharmed)

                    pushTheNotifications(
                        this,
                        theMessageET.text.toString(),
                        theTarget.theUID,
                        theTarget.token!! )
                }else
                {
                    doTheSharmRandomly()
                }
            }

        }.addOnFailureListener(OnFailureListener {

            // HandlingTheSharm.alertoCus(requireContext(), "Something went wrong .. check The Internet")

        })
    }

    fun randomUsers(size:Int?):Int
    {
        return (0 .. size!!).random()
    }

    fun gotoChooseUploadScreen(context: Context) {
        val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
        val intent = Intent(context, TheHome::class.java)
        context.startActivity(intent, bundle)
        finish()
    }

    override fun onBackPressed() {
        val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
        val intent = Intent(this, TheHome::class.java)
        this.startActivity(intent, bundle)
        finish()

    }
}