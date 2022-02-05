package com.example.examplefragmentkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.fragment.FirstFragment
import com.example.examplefragmentkotlin.fragment.SecondFragment
import com.example.examplefragmentkotlin.model.Contact

class RuntimeActivity : AppCompatActivity(), FirstFragment.FirstListener,
    SecondFragment.SecondListener {

    private var firstFragment: FirstFragment? = null
    private var secondFragment: SecondFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime)

        initViews()

    }

    private fun initViews() {
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameFirst, firstFragment!!)
            .replace(R.id.frameSecond, secondFragment!!).commit()
    }


    override fun onFirstSendMessage(str: String?) {
        secondFragment!!.updateSecondMessage(str)
    }

    override fun onFirstSendObject(contact: Contact?) {
        secondFragment!!.updateSecondObject(contact)
    }


    override fun onSecondSendMessage(str: String?) {
        firstFragment!!.updateFirstMessage(str)
    }

    override fun onSecondSendObject(contact: Contact?) {
        firstFragment!!.updateFirstObject(contact)
    }


}