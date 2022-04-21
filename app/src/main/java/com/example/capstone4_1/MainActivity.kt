package com.example.capstone4_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone4_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 캐릭터 정보가 없을 경우 조건 필요 - 현재는 true로 대체함
        if (true) {
            excuteCreateCharacterActivity()
        }
    }

    fun excuteCreateCharacterActivity() {
        val intent = Intent(this@MainActivity, CreateCharacterActivity::class.java)
        startActivity(intent)
    }
}