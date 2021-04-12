package com.d3if0028.spacenet.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.d3if0028.spacenet.R
import com.d3if0028.spacenet.home.dashbord.DashboradFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val fragmentHome = DashboradFragment()
        val fragmentFavorite = FavoriteFragment()
        val fragmentBook = BookFragment()
        val fragmentSetting = SettingFragment()
        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(iv_menu1,R.drawable.ic_baseline_home_active)
            changeIcon(iv_menu2,R.drawable.ic_baseline_favorite_24)
            changeIcon(iv_menu3,R.drawable.ic_baseline_library_books)
            changeIcon(iv_menu4,R.drawable.ic_baseline_settings_24)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentFavorite)
            changeIcon(iv_menu1,R.drawable.ic_baseline_home_24)
            changeIcon(iv_menu2,R.drawable.ic_baseline_favorite_active)
            changeIcon(iv_menu3,R.drawable.ic_baseline_library_books)
            changeIcon(iv_menu4,R.drawable.ic_baseline_settings_24)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentBook)
            changeIcon(iv_menu1,R.drawable.ic_baseline_home_24)
            changeIcon(iv_menu2,R.drawable.ic_baseline_favorite_24)
            changeIcon(iv_menu3,R.drawable.ic_baseline_library_books_active)
            changeIcon(iv_menu4,R.drawable.ic_baseline_settings_24)
        }

        iv_menu4.setOnClickListener {
            setFragment(fragmentSetting)
            changeIcon(iv_menu1,R.drawable.ic_baseline_home_24)
            changeIcon(iv_menu2,R.drawable.ic_baseline_favorite_24)
            changeIcon(iv_menu3,R.drawable.ic_baseline_library_books)
            changeIcon(iv_menu4,R.drawable.ic_baseline_settings_active)
        }


    }

    private fun setFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame,fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int) {
        imageView.setImageResource(int)
    }
}