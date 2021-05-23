package com.d3if0028.spacenet.Learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0028.spacenet.DetailPlanetActivity
import com.d3if0028.spacenet.DetailTataSuryaActivity
import com.d3if0028.spacenet.Model.FaktaTataSurya
import com.d3if0028.spacenet.Model.PlanetModel
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.home.dashbord.FaktaTasSurAdapter
import com.d3if0028.spacenet.home.dashbord.PlanetAdapter
import com.d3if0028.spacenet.home.dashbord.TataSuryaAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fakta__tas_sur.*
import kotlinx.android.synthetic.main.activity_planet.*
import kotlinx.android.synthetic.main.activity_tata_surya.*
import kotlinx.android.synthetic.main.activity_tata_surya.iv_back_tatasurya

class Fakta_TasSur : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    private var datList = ArrayList<FaktaTataSurya>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fakta__tas_sur)
        preferences = Preferences(this!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Fact")
        rv_fact_tata_surya.layoutManager = LinearLayoutManager(this)
        getData()

        iv_back_tatasurya.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(application, "" + databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datList.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    var   fact = getdataSnapshot.getValue(FaktaTataSurya::class.java)
                    datList.add(fact!!)
                }

                rv_fact_tata_surya.adapter = FaktaTasSurAdapter(datList) {

                }
            }
        })
    }
}