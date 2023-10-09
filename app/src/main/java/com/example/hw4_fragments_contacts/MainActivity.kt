package com.example.hw4_fragments_contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4_fragments_contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}