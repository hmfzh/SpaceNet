package com.d3if0028.spacenet.home.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d3if0028.spacenet.Home
import com.d3if0028.spacenet.Learn.Planet
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

//        iv_nama.text = preferences.getValues("nama")
//        tv_email.text = preferences.getValues("email")


        tv_logout.setOnClickListener {
            preferences.setValues("status","0")
            val goHome = Intent((activity), Home::class.java)
            startActivity(goHome)

        }



        tv_data_akun.setOnClickListener{
                val goHome = Intent((activity), DataAcitivty::class.java)
                startActivity(goHome)

        }


        preferences.setValues("logout","0")
        if(preferences.getValues("status").equals("0")){
            val goHome = Intent((activity), SignIn::class.java)
            startActivity(goHome)
        }

        tv_data_akun.setOnClickListener {
            val intent = Intent(activity, DataAkunActivity::class.java)
            startActivity(intent)
        }

        tv_q_n_a.setOnClickListener {
            val intent = Intent(activity, QnAActivity::class.java)
            startActivity(intent)
        }

        tv_info_akun.setOnClickListener {
            val intent = Intent(activity, InfoAppActivity::class.java)
            startActivity(intent)
        }



    }


}