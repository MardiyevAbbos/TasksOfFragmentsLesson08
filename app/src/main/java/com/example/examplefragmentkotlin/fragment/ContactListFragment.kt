package com.example.examplefragmentkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.activity.ContactsActivity
import com.example.examplefragmentkotlin.adapter.ContactAdapter
import com.example.examplefragmentkotlin.model.Contact
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

class ContactListFragment(private val contactsActivity: ContactsActivity) : Fragment() {

    private lateinit var rvContactList: RecyclerView
    private lateinit var adapter: ContactAdapter
    private var listener: ContactListListener? = null
    private var contacts: ArrayList<Contact> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        initViews(view)
        return view
    }


    private fun initViews(view: View) {
        rvContactList = view.findViewById(R.id.rv_contact_list)
        rvContactList.layoutManager = GridLayoutManager(requireContext(), 1)
        refreshAdapter()
    }


    private fun refreshAdapter() {
        contactsActivity.checkPermission()
        prepareContactList()
        Collections.sort(contacts)
        adapter = ContactAdapter(contacts, this)
        rvContactList.adapter = adapter
    }


    fun addNewContact(contact: Contact) {
        adapter.addContact(contact)
    }


    fun clickContactListItem(contact: Contact) {
        listener!!.sendContactToOneC(contact)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is ContactListListener) {
            context
        } else {
            throw RuntimeException("${context.toString()} must implement ContactListListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface ContactListListener {
        fun sendContactToOneC(contact: Contact)
    }


    private fun prepareContactList() {
        val cursor = contactsActivity.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor!!.moveToFirst()

        while (cursor.moveToNext()) {
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    ?: "Unknown"
            val phone =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contact = Contact(name, phone)
            contacts.add(contact)
        }
        cursor.close()

    }

}