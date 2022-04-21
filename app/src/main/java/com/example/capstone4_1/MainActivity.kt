package com.example.capstone4_1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone4_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 캐릭터 정보가 없을 경우 조건 필요 - 현재는 true로 대체함
        if (true) {
            excuteQuestActivity()   // 자기화면 출력하려면 밑에서 함수 만들고 이 줄에 호출하셈
        }
    }

    fun excuteCreateCharacterActivity() {
        val intent = Intent(this@MainActivity, CreateCharacterActivity::class.java)
        startActivity(intent)
    }
    
    fun excuteMyInfoActivity() {
        val intent = Intent(this@MainActivity, MyInfoActivity::class.java)
        startActivity(intent)
    }

    fun excuteQuestActivity() {
        val intent = Intent(this@MainActivity, QuestScreen::class.java)
        startActivity(intent)
    }

}