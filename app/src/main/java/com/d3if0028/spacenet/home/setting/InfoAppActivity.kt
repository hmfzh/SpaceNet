package com.d3if0028.spacenet.home.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if0028.spacenet.R
import kotlinx.android.synthetic.main.activity_info_app.*
import kotlinx.android.synthetic.main.activity_qn_a.*

class InfoAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_app)

        iv_back_about_app.setOnClickListener {
            finish()
        }

    }

}