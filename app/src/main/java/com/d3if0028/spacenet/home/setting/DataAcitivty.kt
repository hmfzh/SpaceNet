package com.d3if0028.spacenet.home.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import kotlinx.android.synthetic.main.activity_data.*

class DataAcitivty : AppCompatActivity() {

    lateinit var  preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        preference = Preferences(this)
        
        et_username.text = preference.getValues("username")
        et_nama.text = preference.getValues("nama")
        et_email.text = preference.getValues("email")
 
    }


}