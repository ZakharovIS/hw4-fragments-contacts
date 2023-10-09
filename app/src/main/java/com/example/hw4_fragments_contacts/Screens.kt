package com.example.hw4_fragments_contacts

import com.example.hw4_fragments_contacts.data.Person
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ContactList() = FragmentScreen { ContactsListFragment() }
    fun ContactDetail(person: Person, index: Int, pos: Int) = FragmentScreen {
        ContactDetailFragment.newInstance(
            person.name.firstName,
            person.name.lastName,
            person.phone,
            person.picture.mediumPic,
            index,
            pos
        )
    }

    fun ContactEdit(
        firstName: String,
        lastName: String,
        phone: String,
        picture: String,
        index: Int,
        pos: Int
    ) = FragmentScreen {
        ContactEditFragment.newInstance(
            firstName,
            lastName,
            phone,
            picture,
            index,
            pos
        )
    }
}