package com.example.capstone4_1

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.capstone4_1.databinding.ActivityMainBinding
import com.example.capstone4_1.fragment.HomeFragment
import com.example.capstone4_1.fragment.MyinfoFragment
import com.example.capstone4_1.fragment.QuestListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val RESPONSE_CREATE_CHARACTER = 50
    var myInfoFragment: Fragment = MyinfoFragment()

    var questScreenFragment: Fragment = QuestListFragment()
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
                R.id.questList -> transaction.replace(R.id.mainFrag, questScreenFragment).commit()
            }
            true
        })
        initialize()

    //        finishQuest.setText(Character.doingQuetstCount.toString() + " / 3")
//        thread(start = true) {
//            var i = 0
//
//            while(i < 10) {
//                i += 1
//
//                runOnUiThread {    //Ui에 접근할 수 있음
//                    finishQuest.text = "카운트 : ${i}"
//                }
//
//                Thread.sleep(1000)	//1000 == 1초
//            }
//        }
    }

    fun refreshFragment(fragment: Fragment, FM: FragmentManager) {
        val ft: FragmentTransaction = FM.beginTransaction()
        ft.detach(fragment).attach(fragment).commit()
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