package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactEditBinding

class ContactEditFragment : Fragment() {

    private val args: ContactDetailFragmentArgs by navArgs()

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
        binding.name.setText(args.person.name.firstName)
        binding.surname.setText(args.person.name.lastName)
        binding.phone.setText(args.person.phone)
        Glide.with(binding.root.context)
            .load(args.person.picture.mediumPic)
            .into(binding.contactPhoto)

        binding.btnSave.setOnClickListener {
            val bundle = Bundle().apply {
                putString("param_firstname", binding.name.text.toString())
                putString("param_lastname", binding.surname.text.toString())
                putString("param_phone", binding.phone.text.toString())
                putInt("param_index", args.index)
                putInt("param_position", args.position)
            }
            setFragmentResult("request_key", bundle)

            findNavController().popBackStack(R.id.contactsListFragment, inclusive = true)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}