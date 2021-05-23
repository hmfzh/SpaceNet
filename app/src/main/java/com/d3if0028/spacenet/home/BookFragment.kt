package com.d3if0028.spacenet.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d3if0028.spacenet.Learn.Fakta_TasSur
import com.d3if0028.spacenet.Learn.Planet
import com.d3if0028.spacenet.Learn.TataSurya
import com.d3if0028.spacenet.R
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      
        return inflater.inflate(R.layout.fragment_book, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_tatasurya.setOnClickListener {
            val intent = Intent(activity, TataSurya::class.java)
            startActivity(intent)
        }

        btn_planet.setOnClickListener {
            val intent = Intent(activity, Planet::class.java)
            startActivity(intent)
        }

        btn_fakta_tata_surya.setOnClickListener {
            val intent = Intent(activity, Fakta_TasSur::class.java)
            startActivity(intent)
        }
    }
}