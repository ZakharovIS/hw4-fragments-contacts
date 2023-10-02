package com.example.hw4_fragments_contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw4_fragments_contacts.data.Person
import com.example.hw4_fragments_contacts.databinding.ItemPersonBinding


class PersonsAdapter(
    private var data: MutableList<Person>,
    private val onClick: (Person, Int) -> Unit) :
    RecyclerView.Adapter<PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = data[position]
        holder.binding.root.setOnClickListener {
            onClick(item, position)
        }
        holder.binding.personName.text = item.name.firstName + " " + item.name.lastName
        holder.binding.phoneNumber.text = item.phone
        Glide.with(holder.binding.root.context)
            .load(item.picture.mediumPic)
            .into(holder.binding.personPhoto)

    }

}

class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)


