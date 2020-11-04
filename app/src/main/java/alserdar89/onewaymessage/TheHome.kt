package alserdar89.onewaymessage

import alserdar89.onewaymessage.loading_and_toast.LoadPics
import alserdar89.onewaymessage.loading_and_toast.LocalUserInfo
import alserdar89.onewaymessage.loading_and_toast.OurToast
import alserdar89.onewaymessage.models.NotificationModel
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class TheHome : AppCompatActivity() {
    private var list: RecyclerView? = null
    private var mDatabase: DatabaseReference? = null
    lateinit var firebaseRecyclerAdapter: FirebaseRecyclerAdapter<NotificationModel, EntryViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_home)


        mDatabase = FirebaseDatabase.getInstance().reference.child("notifications")
            .child(LocalUserInfo.theUID(this))
        mDatabase!!.keepSynced(true)
        list = findViewById<View>(R.id.recyclerView) as RecyclerView
        list!!.setHasFixedSize(true)
        list!!.layoutManager = LinearLayoutManager(this)


        val sendMessage:FloatingActionButton = findViewById(R.id.typeMessage)

        sendMessage.setOnClickListener(View.OnClickListener {

            gotoChooseUploadScreen(this)

        })
    }

    override fun onStart() {
        super.onStart()
        val options: FirebaseRecyclerOptions<NotificationModel> = FirebaseRecyclerOptions.Builder<NotificationModel>()
            .setQuery(mDatabase!!, NotificationModel::class.java)
            .build()

        firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<NotificationModel, EntryViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_item_notification, parent, false)
                return EntryViewHolder(view)


            }



            override fun getItem(position: Int): NotificationModel {
                return super.getItem(super.getItemCount() - position - 1)
            }

            protected override fun onBindViewHolder(
                entryViewHolder: EntryViewHolder,
                i: Int,
                data: NotificationModel
            ) {
                entryViewHolder.setTitle(data.senderName.toString())
                entryViewHolder.setContent(data.theMessage.toString())
                entryViewHolder.setPic(data.senderUID)

                entryViewHolder.mView.setOnClickListener(View.OnClickListener {

                    val bundle = ActivityOptions.makeCustomAnimation(this@TheHome, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
                    val intent = Intent(this@TheHome, TheWholeFuckinMessage::class.java)
                    intent.putExtra("theUID"  , data.senderUID)
                    intent.putExtra("theName"  , data.senderName)
                    intent.putExtra("theMessage"  , data.theMessage)
                    this@TheHome.startActivity(intent, bundle)
                    finish()
                })
            }
        }
        list!!.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter!!.stopListening()
    }

    class EntryViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        var e_title: TextView? = null
        var e_content: TextView? = null
        var e_pic: ImageView? = null
        fun setTitle(title: String?) {
            e_title = mView.findViewById(R.id.senderName) as TextView
            e_title!!.text = title
        }

        fun setContent(content: String?) {
            e_content = mView.findViewById(R.id.senderMessage) as TextView
            e_content!!.text = content
        }

        fun setPic(theUId: String?) {
            e_pic = mView.findViewById(R.id.senderPic) as ImageView
            LoadPics.loadPics(mView.context, e_pic!! , theUId!! )
        }

    }

    fun gotoChooseUploadScreen(context: Context) {
        val bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fui_slide_in_right ,  R.anim.fui_slide_out_left).toBundle()
        val intent = Intent(context, SendTheMessageActivity::class.java)
        context.startActivity(intent, bundle)
        finish()
    }

    override fun onBackPressed() {

        val db = FirebaseFirestore.getInstance()
        val ref = db.collection("UserInformation").document(LocalUserInfo.theUID(baseContext))
        ref.update(
            mapOf(
                "online" to false

            )
        ).addOnSuccessListener { void ->
            OurToast.myToast(this, "You are offline , See you later ")
        }

        finish()
    }
}