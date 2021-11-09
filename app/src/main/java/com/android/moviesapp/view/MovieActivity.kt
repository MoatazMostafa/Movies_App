package com.android.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.moviesapp.R

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}