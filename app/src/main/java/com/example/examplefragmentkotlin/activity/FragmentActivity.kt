package com.example.examplefragmentkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examplefragmentkotlin.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

    }
}