package com.d3if0028.spacenet.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.d3if0028.spacenet.Home
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.sign.signin.SignIn
import kotlinx.android.synthetic.main.fragment_setting.*


/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!.applicationContext)

        iv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
           .into(iv_profile)

        tv_logout.setOnClickListener {
            preferences.setValues("status","0")
            val goHome = Intent((activity), Home::class.java)
            startActivity(goHome)

        }

        tv_change_image.setOnClickListener {
            val goHome = Intent((activity), PhotoScereenActivity::class.java)
            startActivity(goHome)

        }

        preferences.setValues("logout","0")
        if(preferences.getValues("status").equals("0")){
            val goHome = Intent((activity), SignIn::class.java)
            startActivity(goHome)
        }

        iv_profile.setOnClickListener {
            val goHome = Intent((activity), PhotoScereenActivity::class.java)
            startActivity(goHome)
        }
    }


}