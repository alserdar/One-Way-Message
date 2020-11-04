package alserdar89.onewaymessage.loading_and_toast

import alserdar89.onewaymessage.R
import alserdar89.onewaymessage.models.UserModel
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object LoadPics {


    fun loadPicturesInListMessages(context: Context, theSum: Long, pic: ImageView, thePicName: String) {

        /*
         val storageReference = FirebaseStorage.getInstance().reference.child("$theSum privateChat/$thePicName")
        Glide.with(context)
            .using(FirebaseImageLoader())
            .load(storageReference)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .priority(Priority.IMMEDIATE)
            .into(object : BitmapImageViewTarget(pic) {
                override fun setResource(resource: Bitmap?) {
                    val drawable = RoundedBitmapDrawableFactory.create(
                        context.resources,
                        Bitmap.createScaledBitmap(resource!!, 150, 150, false)
                    )
                    drawable.setCircular(false)
                    pic.setImageDrawable(drawable)
                }
            })
         */

    }

    fun loadPics(context: Context, imageView: ImageView , theUID: String)
    {

        try{
            val load = FirebaseStorage.getInstance().reference.child("images/"+ theUID)

            load.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                Glide.with(context).load(uri.toString()).apply(RequestOptions.circleCropTransform())
                    .into(imageView).clearOnDetach().waitForLayout()
            }).addOnFailureListener {

                val db = FirebaseFirestore.getInstance()
                val doc = db.collection("UserInformation").document(theUID)
                doc.get().addOnSuccessListener { documentSnapshot ->

                    if (documentSnapshot.exists())
                    {
                        val user = documentSnapshot.toObject(UserModel::class.java)
                        Glide.with(context).load(user!!.avatar).apply(RequestOptions.circleCropTransform())
                            .into(imageView).clearOnDetach().waitForLayout()

                    }else
                    {
                        imageView.setImageResource(R.mipmap.one_way_message)
                    }

                }
            }
        }catch (e:Exception)
        {
            OurToast.myToast(
                context,
                "Something went wrong"
            )
        }

    }


    fun loadPicsInPrivateChat(context: Context, imageView: ImageView , theUID:String)
    {

        val load = FirebaseStorage.getInstance().reference.child("images/"+ theUID)

        load.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
            // Got the download URL for 'users/me/profile.png'
            // Pass it to Picasso to download, show in ImageView and caching
            Glide.with(context).load(uri.toString()).apply(RequestOptions.circleCropTransform())
                .into(imageView).clearOnDetach().waitForLayout()
        }).addOnFailureListener(OnFailureListener {
            imageView.setImageResource(R.mipmap.one_way_message)
        })

    }


    fun loadCountriesPics(context: Context, imageView: ImageView)
    {

        val db = FirebaseFirestore.getInstance()
        val doc = db.collection("UserInformation").document(
            LocalUserInfo.theUID(
                context
            )
        )
        doc.get().addOnSuccessListener { documentSnapshot ->

            if (documentSnapshot.exists())
            {
                val user = documentSnapshot.toObject(UserModel::class.java)
                val clow = user!!.country!!.toLowerCase()
                val load = FirebaseStorage.getInstance().reference.child("countries/"+ clow+".png" )

                load.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                    // Got the download URL for 'users/me/profile.png'
                    // Pass it to Picasso to download, show in ImageView and caching
                    Glide.with(context).load(uri.toString()).apply(RequestOptions.circleCropTransform())
                        .into(imageView)
                }).addOnFailureListener(OnFailureListener {


                })

            }else
            {
                imageView.setImageResource(R.mipmap.one_way_message)
            }

        }



    }
}