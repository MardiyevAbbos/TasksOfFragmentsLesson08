package com.example.examplefragmentkotlin.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.examplefragmentkotlin.R
import com.example.examplefragmentkotlin.fragment.AddContactFragment
import com.example.examplefragmentkotlin.fragment.ContactListFragment
import com.example.examplefragmentkotlin.fragment.OneContactFragment
import com.example.examplefragmentkotlin.model.Contact

class ContactsActivity : AppCompatActivity(), ContactListFragment.ContactListListener,
    AddContactFragment.AddContactListener {

    private var contactListFragment: ContactListFragment? = null
    private var oneContactFragment: OneContactFragment? = null
    private var addContactFragment: AddContactFragment? = null
    private var ft: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        initViews()

    }

    private fun initViews() {
        contactListFragment = ContactListFragment(this)
        oneContactFragment = OneContactFragment()
        addContactFragment = AddContactFragment()
        ft = supportFragmentManager.beginTransaction()
        addAllFragments()
    }


    private fun addAllFragments() {
        contactListFragment?.let { ft!!.add(R.id.frame_contactList, it, "contactListFragment") }
        oneContactFragment?.let { ft!!.add(R.id.frame_oneContact, it, "contactListFragment") }
        addContactFragment?.let { ft!!.add(R.id.frame_addContact, it, "contactListFragment") }
        ft!!.commit()
    }


    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 909)
        }
    }

    override fun createNewContact(contact: Contact) {
        contactListFragment!!.addNewContact(contact)
    }

    override fun sendContactToOneC(contact: Contact) {
        oneContactFragment!!.updateAllFields(contact)
    }


}