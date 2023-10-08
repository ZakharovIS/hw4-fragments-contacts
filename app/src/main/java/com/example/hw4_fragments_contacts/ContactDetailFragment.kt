package com.example.hw4_fragments_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.databinding.FragmentContactDetailBinding


class ContactDetailFragment : Fragment() {

    private val args: ContactDetailFragmentArgs by navArgs()

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
        binding.name.text = args.person.name.firstName + " " + args.person.name.lastName
        binding.phone.text = args.person.phone
        Glide.with(binding.root.context)
            .load(args.person.picture.mediumPic)
            .into(binding.contactPhoto)
        binding.btnEdit.setOnClickListener {

            findNavController().navigate(
                ContactDetailFragmentDirections.actionContactDetailFragmentToContactEditFragment(
                    args.person,
                    args.index,
                    args.position
                )

            )

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}