package com.example.examplefragmentkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.fragment.ContactListFragment
import com.example.examplefragmentkotlin.model.Contact
import java.util.*
import kotlin.collections.ArrayList

class ContactAdapter(
    private var contacts: ArrayList<Contact>,
    private val contactsActivity: ContactListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContactViewHolder) {
            holder.apply {
                val contact: Contact = contacts[position]
                contactImage.setText(contact.name[0].toString())
                contactName.setText(contact.name)

                clickItem.setOnClickListener {
                    contactsActivity.clickContactListItem(contact)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return contacts.size
    }


    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val clickItem = view.findViewById<LinearLayout>(R.id.click_contactItem)
        val contactImage = view.findViewById<TextView>(R.id.tv_image_item)
        val contactName = view.findViewById<TextView>(R.id.tv_name_item)
    }


    fun addContact(contact: Contact){
        contacts.add(contact)
        Collections.sort(contacts)
        notifyDataSetChanged()
    }

}