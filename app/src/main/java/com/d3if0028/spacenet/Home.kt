package com.d3if0028.spacenet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if0028.spacenet.home.Dashboard
import com.d3if0028.spacenet.sign.signin.SignIn
import com.d3if0028.spacenet.sign.signup.SignUp
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class Home : AppCompatActivity() {

    lateinit var  preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        btn_daftar.setOnClickListener {
            val intent = Intent(this@Home, SignUp::class.java)
            startActivity(intent)
        }

        btn_masuk_2.setOnClickListener {
            val intent = Intent(this@Home, SignIn::class.java)
            startActivity(intent)
        }
    }
}