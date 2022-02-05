package com.example.examplefragmentkotlin.model

data class Contact(val name: String, val number: String) : Comparable<Contact> {

    override fun compareTo(contact: Contact): Int {
        return name.toUpperCase().compareTo(contact.name.toUpperCase())
    }

}
