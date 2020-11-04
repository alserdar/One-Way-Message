package alserdar89.onewaymessage.login

import alserdar89.onewaymessage.*
import alserdar89.onewaymessage.loading_and_toast.LocalUserInfo
import alserdar89.onewaymessage.loading_and_toast.OurToast
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import java.util.*

class GoogleSignInActivity : BaseActivity() {


    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001

    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]

    private var firebaseUser: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sign_in)


        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // [END config_signin]

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseUser = mAuth!!.currentUser
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }

        }
    }


    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        // [START_EXCLUDE silent]
        showProgressDialog()
        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    firebaseUser = mAuth!!.currentUser

                    FirebaseInstanceId.getInstance().instanceId
                        .addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {

                                return@OnCompleteListener
                            }

                            // Get new Instance ID token

                            val privateOrNotDetails = PreferenceManager.getDefaultSharedPreferences(this@GoogleSignInActivity)
                            val editor = privateOrNotDetails.edit()

                            editor.putString("theUID", Objects.requireNonNull<FirebaseUser>(mAuth!!.currentUser).uid)
                            editor.putString("gmail", mAuth!!.currentUser!!.email)
                            editor.putString("avatar", acct.photoUrl.toString())
                            editor.putString("userName", acct.displayName)
                            editor.putString("handle", acct.email!!.substringBefore("@"))
                            editor.putString("handleLowerCase", acct.email!!.substringBefore("@").toLowerCase())
                            editor.putString("id", acct.id)
                            editor.putString("littleId", acct.id.toString().substring(12))
                            editor.putString("token", task.result?.token)
                            editor.apply()

                            buildThatNoob()

                        })

                } else {

                    OurToast.myToast(
                        this,
                        "Something went Wrong ... try again later"
                    )
                    val i = Intent(baseContext, Launcher::class.java)
                    startActivity(i)
                    finish()
                }

                // [START_EXCLUDE]
                hideProgressDialog()
                // [END_EXCLUDE]
            }
    }

    private fun buildThatNoob() {



        val freshUser = HashMap<String, Any>()
        freshUser["theUID"] = LocalUserInfo.theUID(this)
        freshUser["userName"] =
            LocalUserInfo.userName(this)
        freshUser["handle"] = LocalUserInfo.handle(this)
        freshUser["handleLowerCase"] =
            LocalUserInfo.handleLowerCase(this)
        freshUser["gender"] = "male"
        freshUser["avatar"] = LocalUserInfo.avatar(this)
        freshUser["country"] =
            LocalUserInfo.country(this)
        freshUser["town"] = "Unkown"
        freshUser["age"] = "18 : 24"
        freshUser["birthDate"] = ""
        freshUser["relationShip"] = ""
        freshUser["aboutUser"] = ""
        freshUser["linkUser"] = ""
        freshUser["id"] = LocalUserInfo.id(this)
        freshUser["littleId"] =
            LocalUserInfo.littleId(this)
        freshUser["token"] = LocalUserInfo.token(this)
        freshUser["gmail"] = LocalUserInfo.gmail(this)

        freshUser["online"] = false
        freshUser["star"] = false
        freshUser["globallyPerson"] = false
        freshUser["locallyPerson"] = false
        freshUser["paid"] = false
        freshUser["sus"] = false
        freshUser["privateAccount"] = false
        freshUser["hasPic"] = false

        freshUser["reportAccount"] = 0
        freshUser["getX"] = 0

        freshUser["createAccount"] = Date()
        freshUser["lastJoin"] = Date()

        val db = FirebaseFirestore.getInstance()
        db.collection("UserInformation").document(
            LocalUserInfo.theUID(
                this
            )
        )
            .set(freshUser).addOnSuccessListener {

                val i = Intent(baseContext, TheHome::class.java)
                startActivity(i)
                finish()

            }.addOnFailureListener {

                OurToast.myToast(
                    baseContext,
                    "Something went wrong .. try again later"
                )
            }
    }
}