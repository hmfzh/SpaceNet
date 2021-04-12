package com.d3if0028.spacenet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_enter.setOnClickListener {
            var intent = Intent(this@MainActivity,Home::class.java)
            startActivity(intent)
            finish()
        }
    }
}