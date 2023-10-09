package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactEditBinding

private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"
private const val ARG_PARAM5 = "param_index"
private const val ARG_PARAM6 = "param_position"

class ContactEditFragment() : Fragment() {

    private var param_firstname: String? = null
    private var param_lastname: String? = null
    private var param_phone: String? = null
    private var param_picture: String? = null
    private var param_index: Int? = null
    private var param_position: Int? = null

    private var _binding: FragmentContactEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContactEditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            param_firstname = it.getString(ARG_PARAM1)
            param_lastname = it.getString(ARG_PARAM2)
            param_phone = it.getString(ARG_PARAM3)
            param_picture = it.getString(ARG_PARAM4)
            param_index = it.getInt(ARG_PARAM5)
            param_position = it.getInt(ARG_PARAM6)

        }

        binding.name.setText(param_firstname)
        binding.surname.setText(param_lastname)
        binding.phone.setText(param_phone)
        Glide.with(binding.root.context)
            .load(param_picture)
            .into(binding.contactPhoto)

        binding.btnSave.setOnClickListener {
            val bundle = Bundle().apply {
                putString("param_firstname", binding.name.text.toString())
                putString("param_lastname", binding.surname.text.toString())
                putString("param_phone", binding.phone.text.toString())
                putInt("param_index", param_index!!)
                putInt("param_position", param_position!!)
            }

            App.INSTANCE.router.sendResult("request_key", bundle)
            App.INSTANCE.router.backTo(Screens.ContactList())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            param3: String,
            param4: String,
            param5: Int,
            param6: Int
        ) =
            ContactEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putInt(ARG_PARAM5, param5)
                    putInt(ARG_PARAM6, param6)
                }
            }
    }
}