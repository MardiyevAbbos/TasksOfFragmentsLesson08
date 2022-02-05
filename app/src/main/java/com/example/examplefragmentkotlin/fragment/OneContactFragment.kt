package com.example.examplefragmentkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.model.Contact

class OneContactFragment : Fragment() {

    private var tvImage: TextView? = null
    private var tvName: TextView? = null
    private var tvPhone: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_one_contact, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        tvImage = view.findViewById(R.id.oneContact_image)
        tvName = view.findViewById(R.id.oneContact_name)
        tvPhone = view.findViewById(R.id.oneContact_phoneNum)
    }


    fun updateAllFields(contact: Contact) {
        tvImage!!.text = contact.name[0].toString()
        tvName!!.text = contact.name
        tvPhone!!.text = contact.number
    }

}