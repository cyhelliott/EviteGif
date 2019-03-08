package com.example.evitegif

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.evitegif.fragment.GifSearchFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gifSearchFragment = GifSearchFragment()
        supportFragmentManager.beginTransaction()
            .attach(gifSearchFragment)
            .add(R.id.fl_main, gifSearchFragment)
            .commit()
    }
}