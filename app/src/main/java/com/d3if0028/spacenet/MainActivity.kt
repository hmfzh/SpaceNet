package com.d3if0028.spacenet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if0028.spacenet.sign.signin.SignIn
import com.d3if0028.spacenet.sign.signup.SignUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var  preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preference = Preferences(this)

        if(preference.getValues("home").equals("1")){
            finishAffinity()
            val intent = Intent(this@MainActivity, SignIn::class.java)
            startActivity(intent)
        }

        btn_enter.setOnClickListener {
            preference.setValues("home","1")
            finishAffinity()
            var intent = Intent(this@MainActivity,Home::class.java)
            startActivity(intent)
        }
    }
}