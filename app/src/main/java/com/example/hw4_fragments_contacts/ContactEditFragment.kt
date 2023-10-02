package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactEditBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactEditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param_firstname: String? = null
    private var param_lastname: String? = null
    private var param_phone: String? = null
    private var param_picture: String? = null

    private var _binding: FragmentContactEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param_firstname = it.getString(ARG_PARAM1)
            param_lastname = it.getString(ARG_PARAM2)
            param_phone = it.getString(ARG_PARAM3)
            param_picture = it.getString(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactEditBinding.inflate(inflater)

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
            }
            setFragmentResult("request_key", bundle)

            parentFragmentManager.popBackStack(ContactDetailFragment::class.java.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactEditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}