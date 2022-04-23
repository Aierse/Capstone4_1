package com.example.capstone4_1

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

    // 최초 실행 시 초기화 함수
    private fun initialize() {
        // 캐릭터 클래스 초기화
        Character.initializeQuest()

        // 리스트뷰 어댑터 초기화
        binding.mainList.adapter = QuestAdapter(this, Character.questList)
        
        // 기타
        setFrag(CallFragment.MAIN)
    }

    private fun setupEvent() {
        binding.mainList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialog = AlertDialog.Builder(this@MainActivity)

                val selectQuest = parent.getItemAtPosition(position) as Quest
                dialog.setTitle(selectQuest.name)
                dialog.setMessage(selectQuest.explain)

                dialog.setPositiveButton("확인") {dialogInterface, i ->
                    toast("test")
                }

                dialog.setCancelable(false)
                dialog.show()
            }

        binding.btnFrag1.setOnClickListener { setFrag(CallFragment.MYINFO) }
        binding.btnFrag2.setOnClickListener { setFrag(CallFragment.QUSETSCREEN) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initialize()
        setupEvent()

        setContentView(binding.root)
    }

    //토스트 메시지 사용법 -->  기본값 String toast("내용")  형변환 예시 --> toast(변수.toString())
    private fun toast(message: String) {
        var toast: Toast? = null
        // 토스트 메서드
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        } else toast.setText(message)
        toast?.show()
    }

    //시스템 버튼 감지
    override fun onBackPressed() {
        val fm = supportFragmentManager

        if (binding.mainList.visibility == View.VISIBLE) {
            super.onBackPressed()
        }
        //동작 테스트 코드
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.mainList.setVisibility(View.VISIBLE)
    }

    //감지된 화면 번호 받아서 프래그먼트 매니저 동작 처리하는 곳
    private fun setFrag(fragnum: CallFragment) {

        val ft = supportFragmentManager.beginTransaction()
        binding.mainList.adapter = QuestAdapter(this, Character.questList)

        when (fragnum) {
            CallFragment.MAIN -> {
                ft.replace(R.id.mainFrag, MainScreenFragment()).commit()
                binding.mainList.setVisibility(View.VISIBLE)
            }
            CallFragment.MYINFO -> {
                ft.replace(R.id.mainFrag, MyInfoFragment()).addToBackStack(null).commit()
                binding.mainList.setVisibility(View.GONE)
            }
            CallFragment.QUSETSCREEN -> {
                ft.replace(R.id.mainFrag, QuestScreenFragment()).addToBackStack(null).commit()
                binding.mainList.setVisibility(View.GONE)
            }
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

    class QuestScreenFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            this.context.
            val view = inflater.inflate(activity_quest_screen, container, false)
            return view
        }
    }
}