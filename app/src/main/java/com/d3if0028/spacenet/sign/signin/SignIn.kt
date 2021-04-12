package com.d3if0028.spacenet.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d3if0028.spacenet.Dashboard
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignIn : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String
    lateinit var mDatabase: DatabaseReference
    lateinit var  preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference= Preferences(this)


        if(preference.getValues("status").equals("1")){
            finishAffinity()
            val goHome = Intent(this@SignIn, Dashboard::class.java)
            startActivity(goHome)
        }

        btn_masuk.setOnClickListener {
            iUsername = et_email_sign_up.text.toString()
            iPassword = et_nama_sign_up.text.toString()

            if (iUsername.equals("")) {
                et_email_sign_up.setError("Silahkan Tulis Username Anda!")
                et_email_sign_up.requestFocus()
            } else if (iPassword.equals("")) {
                et_nama_sign_up.setError("Silahkan Tulis Password Anda!")
                et_nama_sign_up.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }

        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignIn, "Data tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        preference.setValues("nama" , user.nama.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("password" , user.password.toString())
                        preference.setValues("status","1")
                        val intent = Intent(this@SignIn, Dashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignIn, "Password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignIn,databaseError.message,Toast.LENGTH_LONG).show()
            }
        })
  }
}
