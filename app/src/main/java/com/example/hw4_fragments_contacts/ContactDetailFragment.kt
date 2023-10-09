package com.example.hw4_fragments_contacts


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactDetailBinding

private const val ARG_PARAM1 = "param_firstname"
private const val ARG_PARAM2 = "param_lastname"
private const val ARG_PARAM3 = "param_phone"
private const val ARG_PARAM4 = "param_picture"
private const val ARG_PARAM5 = "param_index"
private const val ARG_PARAM6 = "param_position"

class ContactDetailFragment() : Fragment() {

    private var param_firstname: String? = null
    private var param_lastname: String? = null
    private var param_phone: String? = null
    private var param_picture: String? = null
    private var param_index: Int? = null
    private var param_position: Int? = null


    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater)
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
        binding.name.text = param_firstname + " " + param_lastname
        binding.phone.text = param_phone
        Glide.with(binding.root.context)
            .load(param_picture)
            .into(binding.contactPhoto)



        binding.btnEdit.setOnClickListener {

            Log.d("123", "$param_firstname")
            App.INSTANCE.router.navigateTo(
                Screens.ContactEdit(
                    param_firstname!!,
                    param_lastname!!,
                    param_phone!!,
                    param_picture!!,
                    param_index!!,
                    param_position!!
                )
            )

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
            ContactDetailFragment().apply {
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