package com.d3if0028.spacenet.Learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.d3if0028.spacenet.DetailPlanetActivity
import com.d3if0028.spacenet.DetailTataSuryaActivity
import com.d3if0028.spacenet.Model.PlanetModel
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.home.dashbord.PlanetAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_planet.*

class Planet : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    private var datList = ArrayList<PlanetModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet)
        preferences = Preferences(this!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Planet")
        rv_planet.layoutManager = GridLayoutManager(this,2)
        getData()

        iv_back_planet.setOnClickListener {
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
                    var   planet = getdataSnapshot.getValue(PlanetModel::class.java)
                    datList.add(planet!!)
                }

                rv_planet.adapter = PlanetAdapter(datList) {
                    val intent = Intent(this@Planet, DetailPlanetActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }
        })
    }
}
