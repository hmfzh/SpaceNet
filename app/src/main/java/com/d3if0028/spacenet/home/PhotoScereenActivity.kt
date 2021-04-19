package com.d3if0028.spacenet.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.sign.signin.User
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_photo_scereen.*
import java.util.*

class PhotoScereenActivity : AppCompatActivity(),PermissionListener{
    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var user : User
    private lateinit var mFirebaseDatabase: DatabaseReference
    var imageUri: Uri? = null
    private lateinit var mFirebaseInstance: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_scereen)
        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")


        iv_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                btn_save.visibility = View.INVISIBLE
                iv_add.setImageResource(R.drawable.ic_btn_upload)
                iv_profile.setImageResource(R.drawable.user_pic)

            } else {
//                Dexter.withActivity(this)
//                    .withPermission(android.Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()
                /*ImagePicker.with(this)
                    .cameraOnly()    //User can only capture image using Camera
                    .start()*/
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent, 1)

            }
        }
        btn_save.setOnClickListener {
            if (filePath != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val randomKey = UUID.randomUUID().toString()
                val riversRef = storageReference!!.child("images/$randomKey")


//                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
//                ref.putFile(filePath)
//                    .addOnSuccessListener {
//                        progressDialog.dismiss()
//                        Toast.makeText(this@PhotoScereenActivity, "Uploaded", Toast.LENGTH_SHORT)
//                            .show()
//                        ref.downloadUrl.addOnSuccessListener {
//                            saveToFirebase(it.toString())
//                        }
//                    }
//                    .addOnFailureListener { e ->
//                        progressDialog.dismiss()
//                        Toast.makeText(
//                            this@PhotoScereenActivity,
//                            "Failed " + e.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    .addOnProgressListener { taskSnapshot ->
//                        val progress =
//                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
//                    }

                riversRef.putFile(imageUri!!).addOnSuccessListener {
                    progressDialog.dismiss()
                    saveToFirebase(it.toString())
                    Snackbar.make(findViewById(android.R.id.content), "Image Upload", Snackbar.LENGTH_LONG).show()
                }.addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
                }.addOnProgressListener { snapshot ->
                    val progresPercent = 100.00 * snapshot.bytesTransferred / snapshot.totalByteCount
                    progressDialog.setMessage("Percentage" + progresPercent.toInt() + "%")
                }
            }

        }


    }

    private fun saveToFirebase(url: String) {

        mFirebaseDatabase.child(user.username!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    user.url = url
                    mFirebaseDatabase.child(user.username!!).setValue(user)

                    preferences.setValues("nama", user.nama.toString())
                    preferences.setValues("user", user.username.toString())
                    preferences.setValues("url", "")
                    preferences.setValues("email", user.email.toString())
                    preferences.setValues("status", "1")
                    preferences.setValues("url", url)

                    finishAffinity()
                    val intent = Intent(
                        this@PhotoScereenActivity,
                        SettingFragment::class.java
                    )
                    startActivity(intent)

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@PhotoScereenActivity,
                        "" + error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })


    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Toast.makeText(this, "Anda tidak menambahkan foto profile", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        ImagePicker.with(this)
            .cameraOnly()    //User can only capture image using Camera
            .start()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            statusAdd = true
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            btn_save.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}