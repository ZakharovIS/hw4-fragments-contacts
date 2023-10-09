package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hw4_fragments_contacts.data.Person
import com.example.hw4_fragments_contacts.data.PersonRepository
import com.example.hw4_fragments_contacts.databinding.FragmentContactsListBinding
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"
private const val ARG_PARAM5 = "param_index"
private const val ARG_PARAM6 = "param_position"

class ContactsListFragment : Fragment() {

    private val personRepository = PersonRepository()
    private var personList: MutableList<Person> = mutableListOf()
    private lateinit var adapter: PersonsAdapter

    private var updatedPersonIndex: Int = 0
    private var updatedPersonPosition: Int = 0

    private var binding: FragmentContactsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        setFragmentResultListener("request_key") { requestKey, bundle ->
            updatedPersonIndex = bundle.getInt(ARG_PARAM5)
            updatedPersonPosition = bundle.getInt(ARG_PARAM6)
            personList.elementAt(updatedPersonIndex).name.firstName = bundle.getString(ARG_PARAM1)!!
            personList.elementAt(updatedPersonIndex).name.lastName = bundle.getString(ARG_PARAM2)!!
            personList.elementAt(updatedPersonIndex).phone = bundle.getString(ARG_PARAM3)!!
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

        val bundle = Bundle().apply {
            putString("param_firstname", person.name.firstName)
            putString("param_lastname", person.name.lastName)
            putString("param_phone", person.phone)
            putString("param_picture", person.picture.mediumPic)
            putInt("param_index", personList.indexOf(person))
            putInt("param_position", position)
        }

        findNavController().navigate(R.id.action_contactsListFragment_to_contactDetailFragment, bundle)

    }

}