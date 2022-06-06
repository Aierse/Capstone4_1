package com.example.capstone4_1

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.capstone4_1.databinding.ActivityMainBinding
import com.example.capstone4_1.fragment.HomeFragment
import com.example.capstone4_1.fragment.MyinfoFragment
import com.example.capstone4_1.fragment.QuestListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.io.File
import java.time.Duration
import java.time.LocalTime
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val RESPONSE_CREATE_CHARACTER = 50
    lateinit var thread: Thread

    var myInfoFragment: Fragment = MyinfoFragment()
    var questListFragment: Fragment = QuestListFragment()
    var homeFragment: Fragment = HomeFragment()
    var FM = supportFragmentManager

    // 최초 실행 시 초기화 함수
    private fun initialize() {
        // 캐릭터 클래스 초기화
        val filepath = filesDir.toString() + "/data.json"
        val file = File(filepath)

        if (file.exists()) { // 파일이 존재 할경우
            Character.loadCharacter(this)
            findViewById<BottomNavigationView>(R.id.menu_bottom_navigation).selectedItemId =
                R.id.home
        } else { // 아닐경우
            excuteCreateCharacterActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(Intent(this, AutoSave::class.java))


        // setupEvent()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.menu_bottom_navigation)

        //프래그먼트 메뉴
        bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            val transaction = FM.beginTransaction()

            when (item.itemId) {
                R.id.myInfo -> transaction.replace(R.id.mainFrag, myInfoFragment).commit()
                R.id.home -> transaction.replace(R.id.mainFrag, homeFragment).commit()
                R.id.questList -> transaction.replace(R.id.mainFrag, questListFragment).commit()
            }
            true
        })
        initialize()

        thread(start = true) {
            while (true) {
                Character.remainTime = remainQuestTime()
                binding.remainTime.setText(Character.remainTime)
                Log.d("aaaaaa", "TIme" + Character.remainTime)
                Thread.sleep(1000)
            }
            thread.isDaemon
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RESPONSE_CREATE_CHARACTER -> {
                findViewById<BottomNavigationView>(R.id.menu_bottom_navigation).selectedItemId =
                    R.id.home
            }
        }
    }

    //시스템 버튼 감지
    override fun onBackPressed() {
        finish()
    }

    //내 정보 생성
    private fun excuteCreateCharacterActivity() {
        val intent = Intent(this, CreateCharacterActivity::class.java)
        startActivityForResult(intent, RESPONSE_CREATE_CHARACTER)
    }

    override fun onDestroy() {
        Character.saveCharacter(this)
        super.onDestroy()
    }
}

class AutoSave : Service() {
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onTaskRemoved(rootIntent: Intent) { //핸들링 하는 부분
        Character.saveCharacter(this)

    }
}

fun remainQuestTime(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val remainQuestTime: LocalTime
        val nowTime = LocalTime.now()
        val resetTime = LocalTime.parse("06:00:00") // stub code must be changed
        var duration = Duration.between(resetTime, nowTime).seconds
//            Log.d("Duration :","${duration.toString()}" )
        if (duration < 0) {
            duration = -1 * duration
            val hour = duration / 3600
            duration %= 3600
            val minutes = duration / 60
            duration %= 60
            val seconds = duration
            remainQuestTime = LocalTime.of(hour.toInt(), minutes.toInt(), seconds.toInt())
        } else {
            duration = 86400 - duration
            val hour = duration / 3600
//                Log.d("hours :" ,"${hour.toString()}")
            duration %= 3600
            val minutes = duration / 60
//                Log.d("hours :" ,"${minutes.toString()}")
            duration %= 60
            val seconds = duration
//                Log.d("seconds :" , "${seconds.toString()}")
            remainQuestTime = LocalTime.of(hour.toInt(), minutes.toInt(), seconds.toInt())
        }

        return remainQuestTime.toString()
    }
    return ""
}