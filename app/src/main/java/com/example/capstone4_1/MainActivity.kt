package com.example.capstone4_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.capstone4_1.R.layout.*
import com.example.capstone4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFrag(1)
        binding.btnFrag2.setOnClickListener {
            setFrag(1)
        }
        binding.btnFrag3.setOnClickListener {
            setFrag(2)
        }

    }

    private fun setFrag(fragnum: Int) {
        val ft = supportFragmentManager.beginTransaction()

        when (fragnum) {
            0 -> {
                ft.replace(R.id.mainFrag, Fragment1()).commit()
            }
            1 -> {
                ft.replace(R.id.mainFrag, Fragment2()).commit()
            }
            2 -> {
                ft.replace(R.id.mainFrag, Fragment3()).commit()
            }
        }
    }
}

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_create_character, container, false)
        return view
    }
}

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_my_info, container, false)
        return view
    }
}

class Fragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_quest_screen, container, false)
        return view
    }
}
