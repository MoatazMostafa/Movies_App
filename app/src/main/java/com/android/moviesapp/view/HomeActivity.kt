package com.android.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.moviesapp.R
import com.android.moviesapp.view.fragments.HomeFragment
import com.android.moviesapp.view.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView.background=null
        makeCurrentFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_bar_home -> makeCurrentFragment(HomeFragment())
                R.id.bottom_bar_search -> makeCurrentFragment(SearchFragment())
            }
            true
        }
    }
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment,fragment)
            commit()
        }
    }
}