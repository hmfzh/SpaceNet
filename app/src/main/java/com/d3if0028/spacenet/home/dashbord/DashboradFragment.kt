package com.d3if0028.spacenet.home.dashbord

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0028.spacenet.Preferences
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.Model.Berita
import com.google.firebase.database.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.d3if0028.spacenet.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.rv_data
import kotlinx.android.synthetic.main.fragment_home.rv_information

class DashboradFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    private var datList = ArrayList<Berita>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dashborad, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Langit")


        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
           //.into(iv_profile)

        rv_data.layoutManager = GridLayoutManager(context,2)
       rv_information.layoutManager = LinearLayoutManager(context)
        getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "" + databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datList.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    var berita = getdataSnapshot.getValue(Berita::class.java)
                    datList.add(berita!!)
                }

                if (datList.isNotEmpty()) {
                    rv_data.adapter = DataLangit(datList) {
                        val intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                        startActivity(intent)
                    }

                    rv_information.adapter = AstronomiAdapter(datList) {
                        val intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                        startActivity(intent)
                    }

                }


            }


        })
    }



}

