package com.d3if0028.spacenet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.d3if0028.spacenet.Model.Berita
import com.d3if0028.spacenet.Model.SumberAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<SumberAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Berita>("data")



            mDatabase = FirebaseDatabase.getInstance().getReference("Langit")
            tv_judul.text = data!!.judul
            tv_desc.text = data!!.desc
            Glide.with(this)
                    .load(data.poster)
                    .into(iv_poster_detail)

//
        iv_back.setOnClickListener {
            finish()
        }
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val berita = getdataSnapshot.getValue(SumberAdapter::class.java!!)
                    dataList.add(berita!!)
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
      }

}