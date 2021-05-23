package com.d3if0028.spacenet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.d3if0028.spacenet.Model.PlanetModel
import com.d3if0028.spacenet.Model.Solarsystem
import com.d3if0028.spacenet.Model.Sumber
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.iv_poster_detail
import kotlinx.android.synthetic.main.activity_detail.tv_desc
import kotlinx.android.synthetic.main.activity_detail.tv_judul
import kotlinx.android.synthetic.main.activity_detail_planet.*
import kotlinx.android.synthetic.main.activity_detail_tata_surya.*
import kotlinx.android.synthetic.main.activity_detail_tata_surya.iv_back_detail_tatasurya

class DetailPlanetActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Sumber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_planet)
        val data = intent.getParcelableExtra<PlanetModel>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Planet")

        tv_judul.text = data!!.judul
        tv_desc.text = data!!.desc
        tv_bulan.text = data!!.bulan
        tv_diameter.text = data!!.diameter
        tv_radius.text = data!!.radius
        tv_panjang_tahun.text = data!!.tahun
        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_detail)

        iv_back_detail_tatasurya.setOnClickListener {
            finish()
        }

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val tatasurya = getdataSnapshot.getValue(Sumber::class.java!!)
                    dataList.add(tatasurya!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailPlanetActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}