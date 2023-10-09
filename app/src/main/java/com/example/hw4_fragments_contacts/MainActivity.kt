package com.example.hw4_fragments_contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4_fragments_contacts.databinding.FragmentContactEditBinding
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private var _binding: FragmentContactEditBinding? = null
    private val binding get() = _binding!!

    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val screen = Screens.ContactList()
        App.INSTANCE.router.replaceScreen(screen)


    }
}