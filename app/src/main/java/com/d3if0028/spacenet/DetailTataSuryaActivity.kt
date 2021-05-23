package com.d3if0028.spacenet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.d3if0028.spacenet.Model.Berita
import com.d3if0028.spacenet.Model.Solarsystem
import com.d3if0028.spacenet.Model.Sumber
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.iv_poster_detail
import kotlinx.android.synthetic.main.activity_detail.tv_desc
import kotlinx.android.synthetic.main.activity_detail.tv_judul
import kotlinx.android.synthetic.main.activity_detail_tata_surya.*

class DetailTataSuryaActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Sumber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tata_surya)

        val data = intent.getParcelableExtra<Solarsystem>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Solarsystem")

        tv_judul.text = data!!.judul
        tv_desc.text = data!!.desc
        tv_content.text = data!!.content
        tv_sumber.text = data!!.sumber
        tv_title.text = data!!.title
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
                Toast.makeText(this@DetailTataSuryaActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}