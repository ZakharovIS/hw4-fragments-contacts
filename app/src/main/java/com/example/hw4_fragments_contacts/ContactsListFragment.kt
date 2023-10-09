package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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


    private var binding: FragmentContactsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()

        App.INSTANCE.router.setResultListener("request_key") {data ->

            val updatedPersonIndex = (data as Bundle).getInt(ARG_PARAM5)
            val updatedPersonPosition = data.getInt(ARG_PARAM6)
            personList.elementAt(updatedPersonIndex).name.firstName = data.getString(ARG_PARAM1)!!
            personList.elementAt(updatedPersonIndex).name.lastName = data.getString(ARG_PARAM2)!!
            personList.elementAt(updatedPersonIndex).phone = data.getString(ARG_PARAM3)!!
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

        App.INSTANCE.router.navigateTo(
            Screens.ContactDetail(
                person,
                personList.indexOf(person),
                position
            )
        )

    }


}