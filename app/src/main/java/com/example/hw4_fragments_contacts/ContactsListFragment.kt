package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.hw4_fragments_contacts.data.Person
import com.example.hw4_fragments_contacts.data.PersonRepository
import com.example.hw4_fragments_contacts.databinding.FragmentContactsListBinding
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"

class ContactsListFragment : Fragment() {

    private var param_firstname: String? = null
    private var param_lastname: String? = null
    private var param_phone: String? = null
    private var param_picture: String? = null

    private val personRepository = PersonRepository()
    private var personList: MutableList<Person> = mutableListOf()
    private lateinit var adapter: PersonsAdapter

    private var updatedPersonIndex: Int = 0
    private var updatedPersonPosition: Int = 0

    private var binding: FragmentContactsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        arguments?.let {
            param_firstname = it.getString(ARG_PARAM1)
            param_lastname = it.getString(ARG_PARAM2)
            param_phone = it.getString(ARG_PARAM3)
            param_picture = it.getString(ARG_PARAM4)
        }
        setFragmentResultListener("request_key") { requestKey, bundle ->
            personList.elementAt(updatedPersonIndex).name.firstName = bundle.getString("param_firstname")!!
            personList.elementAt(updatedPersonIndex).name.lastName = bundle.getString("param_lastname")!!
            personList.elementAt(updatedPersonIndex).phone = bundle.getString("param_phone")!!
            adapter.notifyItemChanged(updatedPersonPosition)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(inflater)

        adapter = PersonsAdapter(personList, onClick = { item, pos -> onClick(item, pos) })
        binding!!.RVContacts.adapter = adapter
        return binding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.RVContacts?.adapter = null
        binding = null
    }

    private fun loadData() {
        lifecycleScope.launch {
            personList.addAll(personRepository.getPersons())
            adapter.notifyDataSetChanged()
        }
    }

    private fun onClick(person: Person, position: Int) {

        updatedPersonIndex = personList.indexOf(person)
        updatedPersonPosition = position
        val bundle = Bundle().apply {
            putString("param_firstname", person.name.firstName)
            putString("param_lastname", person.name.lastName)
            putString("param_phone", person.phone)
            putString("param_picture", person.picture.mediumPic)
        }

        parentFragmentManager.commit {
            replace<ContactDetailFragment>(containerViewId = R.id.fragment_container, args = bundle)
            addToBackStack(ContactDetailFragment::class.java.simpleName)
        }
    }

}