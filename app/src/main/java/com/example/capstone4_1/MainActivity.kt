package com.example.capstone4_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.capstone4_1.R.layout.activity_my_info
import com.example.capstone4_1.R.layout.activity_quest_screen
import com.example.capstone4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: com.example.capstone4_1.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Adapter = QuestAdapter(this, Character.QuestList)
        val listview = findViewById<View>(R.id.mainList) as ListView
        listview.adapter = Adapter

        listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectItem = parent.getItemAtPosition(position) as Quest
                selectItem.Qname
                toast(selectItem.Qname)
            }
        binding.btnFrag1.setOnClickListener {
            setFrag(0)
            binding.mainList.setVisibility(View.INVISIBLE)
        }
        binding.btnFrag2.setOnClickListener {
            binding.mainList.setVisibility(View.VISIBLE)
            setFrag(1)
            /* 퀘스트페이지  화면 전환으로 실행 코드 (필요시 활성화 예정)
            val intent = Intent(this, QuestScreen::class.java)
            startActivity(intent)*/
        }
    }

    //토스트 메시지 사용법 -->  기본값 String toast("내용")  형변환 예시 --> 변수.toString()
    private fun toast(message: String) {
        var toast: Toast? = null
        // 토스트 메서드
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        } else toast.setText(message)
        toast?.show()
    }

    private fun setFrag(fragnum: Int) {

        val ft = supportFragmentManager.beginTransaction()

        when (fragnum) {
            0 -> {
                ft.replace(R.id.mainFrag, MyInfoFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.mainFrag, QuestScreenFragment()).commit()
            }
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







