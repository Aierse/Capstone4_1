package com.example.capstone4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone4_1.databinding.ActivityCreateCharacterBinding

class CreateCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}