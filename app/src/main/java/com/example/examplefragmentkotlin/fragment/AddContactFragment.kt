package com.example.examplefragmentkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.model.Contact
import java.lang.RuntimeException

class AddContactFragment : Fragment() {

    var listener: AddContactListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        var contactName = view.findViewById<EditText>(R.id.edt_name_addContact)
        var contactNumber = view.findViewById<EditText>(R.id.edt_phoneNum_addContact)
        val btnAdd = view.findViewById<Button>(R.id.btn_add_addContact)

        btnAdd.setOnClickListener {
            if (!contactName.text.isEmpty() && !contactNumber.text.isEmpty()) {
                listener!!.createNewContact(
                    Contact(
                        contactName.text.toString(),
                        contactNumber.text.toString()
                    )
                )

                contactName.setText("")
                contactNumber.setText("")
            }
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is AddContactListener) {
            context
        } else {
            throw RuntimeException("${context.toString()} must implement AddContactListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface AddContactListener {
        fun createNewContact(contact: Contact)
    }

}