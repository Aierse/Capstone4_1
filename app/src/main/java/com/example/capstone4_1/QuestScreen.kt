package com.example.capstone4_1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class QuestScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_screen)

        //화면 전환으로 실행 하기 (필요시 활성화 예정)
        val Adapter = QuestAdapter(this, Character.questList)
        val listview = findViewById<View>(R.id.questlist) as ListView
        listview.adapter = Adapter

        listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectItem = parent.getItemAtPosition(position) as Quest
                toast(selectItem.name)
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
}

