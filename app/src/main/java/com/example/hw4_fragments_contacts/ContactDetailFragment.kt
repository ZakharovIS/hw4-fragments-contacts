package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactDetailBinding

private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"
private const val ARG_PARAM5 = "param_index"
private const val ARG_PARAM6 = "param_position"

class ContactDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param_firstname: String? = null
    private var param_lastname: String? = null
    private var param_phone: String? = null
    private var param_picture: String? = null
    private var param_index: Int? = null
    private var param_position: Int? = null

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param_firstname = it.getString(ARG_PARAM1)
            param_lastname = it.getString(ARG_PARAM2)
            param_phone = it.getString(ARG_PARAM3)
            param_picture = it.getString(ARG_PARAM4)
            param_index = it.getInt(ARG_PARAM5)
            param_position = it.getInt(ARG_PARAM6)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = param_firstname + " " + param_lastname
        binding.phone.text = param_phone
        Glide.with(binding.root.context)
            .load(param_picture)
            .into(binding.contactPhoto)
        binding.btnEdit.setOnClickListener {
            val bundle = Bundle().apply {
                putString("param_firstname", param_firstname)
                putString("param_lastname", param_lastname)
                putString("param_phone", param_phone)
                putString("param_picture", param_picture)
                putInt("param_index", param_index!!)
                putInt("param_position", param_position!!)
            }

            parentFragmentManager.commit {
                replace<ContactEditFragment>(containerViewId = R.id.fragment_container, args = bundle)
                addToBackStack(ContactEditFragment::class.java.simpleName)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}