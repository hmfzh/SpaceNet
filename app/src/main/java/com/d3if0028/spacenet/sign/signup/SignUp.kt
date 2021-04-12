package com.d3if0028.spacenet.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.sign.signin.SignIn
import com.d3if0028.spacenet.sign.signin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String
    lateinit var  mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase : DatabaseReference
    lateinit var mDatabaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_signup_daftar.setOnClickListener{
            sEmail = et_email_sign_up.text.toString()
            sNama = et_nama_sign_up.text.toString()
            sUsername = et_signup_username.text.toString()
            sPassword = et_signup_password.text.toString()

            if (sEmail.equals("")) {
                et_email_sign_up.error = "Silakan masukan email anda"
                et_email_sign_up.requestFocus()
            } else if (sUsername.equals("")) {
                et_signup_username.error = "Silakan masukan username anda"
                et_signup_username.requestFocus()
            }else if (sNama.equals("")) {
                et_nama_sign_up.error = "Silakan masukan nama anda"
                et_nama_sign_up.requestFocus()
            } else if (sPassword.equals("")) {
                et_signup_password.error = "Silakan masukan password anda"
                et_signup_password.requestFocus()
            } else{
                saveUsername(sUsername,sPassword,sNama,sEmail)
            }
        }
    }
    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.nama = sNama
        user.username = sUsername
        user.password = sPassword

        if (sUsername != null){
            checkingUsername(sUsername,user)
        }

    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(sUsername).setValue(data)
                    var goSignUpPhotoscreenn = Intent(this@SignUp,
                            SignIn::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhotoscreenn)
                } else {
                    Toast.makeText(this@SignUp, "User telah digunakan", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUp, "" + databaseError.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}