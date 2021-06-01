package com.d3if0028.spacenet.home.setting

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_data_akun.*
import kotlinx.android.synthetic.main.activity_detail_tata_surya.*
import java.io.ByteArrayOutputStream

class DataAkunActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CAMERA = 100
    }

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                R.layout.activity_data_akun)

        preferences = Preferences(this!!.applicationContext)
        tv_nama_akun.text = preferences.getValues("nama")
        tv_email_akun.text = preferences.getValues("email")
        tv_username_akun.text = preferences.getValues("username")

        iv_back_tatasurya.setOnClickListener {
            finish()
        }

        ivProfile.setOnClickListener {
            intentCamera()
        }


    }


    private fun intentCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ intent ->
            intent.resolveActivity(packageManager).also {
                startActivityForResult(intent, REQUEST_CAMERA)
            }
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("images/${FirebaseAuth.getInstance().currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val image = baos.toByteArray()

        ref.putBytes(image).addOnCompleteListener{
            if (it.isSuccessful){
                ref.downloadUrl.addOnCompleteListener {
                    it.result?.let {
                        imageUri = it
                        ivProfile.setImageBitmap(imgBitmap)
                    }
                }
            }
        }
    }
}