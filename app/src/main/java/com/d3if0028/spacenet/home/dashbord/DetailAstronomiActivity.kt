package com.d3if0028.spacenet.home.dashbord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.d3if0028.spacenet.Model.Berita
import com.d3if0028.spacenet.Model.Sumber
import com.d3if0028.spacenet.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailAstronomiActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Sumber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_astronomi)

        val data = intent.getParcelableExtra<Berita>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Langit")


        tv_judul.text = data!!.title
        tv_desc.text = data!!.content
        Glide.with(this)
                .load(data.images)
                .into(iv_poster_detail)

//
        iv_back_tatasurya.setOnClickListener {
            finish()
        }

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val berita = getdataSnapshot.getValue(Sumber::class.java!!)
                    dataList.add(berita!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailAstronomiActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}