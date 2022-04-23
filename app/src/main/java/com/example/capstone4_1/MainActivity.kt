package com.example.capstone4_1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.capstone4_1.R.layout.*
import com.example.capstone4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: com.example.capstone4_1.databinding.ActivityMainBinding
//    private lateinit var binding1: com.example.capstone4_1.databinding.ActivityQuestScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding1 = ActivityQuestScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(binding1.root)

        Character.initializeQuest()

        //최초실행 프래그먼트(앱 실행시 출력되는 화면)
        setFrag(0)

        //퀘스트 읽기 리스트 동작
        val Adapter = QuestAdapter(this, Character.questList)
        val listview = findViewById<View>(R.id.mainList) as ListView
        listview.adapter = Adapter

        //퀘스트 선택 감지 동작 (현재는 퀘스트 이름이 출력이 되는 코드)
        listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectItem = parent.getItemAtPosition(position) as Quest
                selectItem.Qname
                toast(selectItem.Qname)
            }

        //메뉴버튼 클릭 감지
        binding.btnFrag1.setOnClickListener { setFrag(1) }
        binding.btnFrag2.setOnClickListener {
            setFrag(2)
             //퀘스트페이지  화면 전환으로 실행 코드 (필요시 활성화 예정)
            val intent = Intent(this, QuestScreen::class.java)
            startActivity(intent)
        }

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
        //동작 테스트 코드
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.mainList.setVisibility(View.VISIBLE)
    }

    //감지된 화면 번호 받아서 프래그먼트 매니저로 처리하는 곳
    private fun setFrag(fragnum: Int) {

        val ft = supportFragmentManager.beginTransaction()

        when (fragnum) {
            0 -> {
                ft.replace(R.id.mainFrag, MainScreenFragment()).commit()
                binding.mainList.setVisibility(View.VISIBLE)
            }
            1 -> {
                ft.replace(R.id.mainFrag, MyInfoFragment()).addToBackStack(null).commit()
                binding.mainList.setVisibility(View.GONE)
            }
            2 -> {
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
            val view = inflater.inflate(activity_quest_screen, container, false)
            return view
        }
    }
}







