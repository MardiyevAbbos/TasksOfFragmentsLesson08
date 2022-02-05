package com.example.examplefragmentkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examplefragmentkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        val btnFragmentActivity = findViewById<Button>(R.id.open_fragmentActivity)

        val btnRuntimeActivity = findViewById<Button>(R.id.open_runtimeActivity)
        btnRuntimeActivity.setOnClickListener {
            openRuntimeActivity()
        }

        val btnContactsActivity = findViewById<Button>(R.id.open_contactsActivity)
        btnContactsActivity.setOnClickListener {
            openContactsActivity()
        }

    }


    private fun openFragmentActivity() {
        val intent = Intent(this, FragmentActivity::class.java)
        startActivity(intent)
    }


    private fun openRuntimeActivity() {
        val intent = Intent(this, RuntimeActivity::class.java)
        startActivity(intent)
    }


    private fun openContactsActivity() {
        val intent = Intent(this, ContactsActivity::class.java)
        startActivity(intent)
    }

}