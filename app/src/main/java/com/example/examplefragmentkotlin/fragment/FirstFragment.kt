package com.example.examplefragmentkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.model.Contact
import java.lang.RuntimeException

class FirstFragment : Fragment() {

    private var listener: FirstListener? = null
    private var tvFirstMessage: TextView? = null
    private var tvFirstObject: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        initViews(view)
        return view
    }


    private fun initViews(view: View) {
        tvFirstMessage = view.findViewById(R.id.tv_first_message)
        tvFirstObject = view.findViewById(R.id.tv_first_object)
        val btnFirstMessage = view.findViewById<Button>(R.id.btn_first_message)
        btnFirstMessage.setOnClickListener {
            listener!!.onFirstSendMessage("PDP")
        }

        val btnFirstObject = view.findViewById<Button>(R.id.btn_first_object)
        btnFirstObject.setOnClickListener {
            listener!!.onFirstSendObject(Contact("Azamat", "999660934"))
        }

    }


    fun updateFirstMessage(str: String?) {
        tvFirstMessage!!.text = str
    }


    fun updateFirstObject(contact: Contact?) {
        tvFirstObject!!.text = "${contact!!.name}: ${contact!!.number}"
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is FirstListener) {
            context
        } else {
            throw RuntimeException(context.toString() + "must implement FirstListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface FirstListener {
        fun onFirstSendMessage(str: String?)
        fun onFirstSendObject(contact: Contact?)
    }

}