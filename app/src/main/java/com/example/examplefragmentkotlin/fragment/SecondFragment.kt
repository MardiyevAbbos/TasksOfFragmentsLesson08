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

class SecondFragment : Fragment() {

    private var listener: SecondListener? = null
    private var tvSecondMessage: TextView? = null
    private var tvSecondObject: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        tvSecondMessage = view.findViewById(R.id.tv_second_message)
        tvSecondObject = view.findViewById(R.id.tv_second_object)
        val btnSecondMessage = view.findViewById<Button>(R.id.btn_second_message)
        btnSecondMessage.setOnClickListener {
            listener!!.onSecondSendMessage("Academy")
        }

        val btnSendObject = view.findViewById<Button>(R.id.btn_second_object)
        btnSendObject.setOnClickListener {
            listener!!.onSecondSendObject(Contact("Nematillo", "995585858"))
        }
    }


    fun updateSecondMessage(str: String?) {
        tvSecondMessage!!.text = str
    }


    fun updateSecondObject(contact: Contact?) {
        tvSecondObject!!.text = "${contact!!.name}: ${contact!!.number}"
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is SecondListener) {
            context
        } else {
            throw RuntimeException("${context.toString()} must implement SecondListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface SecondListener {
        fun onSecondSendMessage(str: String?)
        fun onSecondSendObject(contact: Contact?)
    }

}