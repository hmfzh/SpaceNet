package com.d3if0028.spacenet.Learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0028.spacenet.DetailActivity
import com.d3if0028.spacenet.DetailTataSuryaActivity
import com.d3if0028.spacenet.Model.Solarsystem
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.home.dashbord.TataSuryaAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_tata_surya.*
import kotlinx.android.synthetic.main.activity_tata_surya.iv_back_tatasurya

class TataSurya : AppCompatActivity() {
//
    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    private var datList = ArrayList<Solarsystem>()
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tata_surya)
        preferences = Preferences(this!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Solarsystem")
        rv_tata_surya.layoutManager = LinearLayoutManager(this)
        getData()

    iv_back_tatasurya.setOnClickListener {
        finish()
    }

    }
//
//    fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_tata_surya)
//
//
//    }
//
private fun getData() {
    mDatabase.addValueEventListener(object : ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {
            Toast.makeText(application, "" + databaseError.message, Toast.LENGTH_LONG).show()
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            datList.clear()
            for (getdataSnapshot in dataSnapshot.children) {
                var   solarsystem = getdataSnapshot.getValue(Solarsystem::class.java)
                datList.add(solarsystem!!)
            }


                rv_tata_surya.adapter = TataSuryaAdapter(datList) {
                    val intent = Intent(this@TataSurya, DetailTataSuryaActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }




        }


    })
}
}


