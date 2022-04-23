package com.example.capstone4_1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.capstone4_1.R.layout.*
import com.example.capstone4_1.databinding.ActivityMainBinding

enum class CallFragment {
    MAIN, MYINFO, QUSETSCREEN
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    // 최초 실행 시 초기화 함수
    private fun initialize() {
        // 캐릭터 클래스 초기화
        Character.initializeQuest()




        // 기타
        setFrag(CallFragment.MAIN)
    }

    private fun setupEvent() {
        binding.btnFrag1.setOnClickListener { setFrag(CallFragment.MYINFO) }
        binding.btnFrag2.setOnClickListener { setFrag(CallFragment.QUSETSCREEN) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initialize()
        setupEvent()
        excuteCreateCharacterActivity()


        setContentView(binding.root)

    }

    //시스템 버튼 감지
    override fun onBackPressed() {
        val fm = supportFragmentManager

        if (binding.mainFrag.visibility == View.VISIBLE) {
            super.onBackPressed()
        }
        //동작 테스트 코드
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.mainFrag.setVisibility(View.VISIBLE)
    }

    //감지된 화면 번호 받아서 프래그먼트 매니저 동작 처리하는 곳
    private fun setFrag(fragnum: CallFragment) {

        val ft = supportFragmentManager.beginTransaction()

        when (fragnum) {
            CallFragment.MAIN -> {
                ft.replace(R.id.mainFrag, MainScreenFragment()).commit()
            }
            CallFragment.MYINFO -> {
                ft.replace(R.id.mainFrag, MyInfoFragment()).addToBackStack(null).commit()
            }
            CallFragment.QUSETSCREEN -> {
                ft.replace(R.id.mainFrag, QuestScreenFragment()).addToBackStack(null).commit()
            }
        }
    }
    private fun excuteCreateCharacterActivity() {
        val intent = Intent(this, CreateCharacterActivity::class.java)
        startActivity(intent)
    }
}

//프래그먼트별 동작
class MainScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_main_screen, container, false)

        val rootView = view.findViewById<ListView>(R.id.mainFrag)

        return view
    }
}

class MyInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_my_info, container, false)
        return view
    }
}

class QuestListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(quest_list, container, false)

        val rootView = view.findViewById<ListView>(R.id.questlist)
        val adapter = QuestAdapter(requireContext())

        rootView.adapter = adapter

        return view
    }
}

class QuestScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(activity_quest_screen, container, false)
        return view
    }
}