package com.d3if0028.spacenet.home.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if0028.spacenet.R
import kotlinx.android.synthetic.main.activity_data_akun.*
import kotlinx.android.synthetic.main.activity_qn_a.*

class QnAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qn_a)

        iv_back_qna.setOnClickListener {
            finish()
        }

    }
}