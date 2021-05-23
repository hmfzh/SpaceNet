package com.d3if0028.spacenet.home.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_data_akun.*
import kotlinx.android.synthetic.main.activity_detail_tata_surya.*

class DataAkunActivity : AppCompatActivity() {
    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

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


    }
}