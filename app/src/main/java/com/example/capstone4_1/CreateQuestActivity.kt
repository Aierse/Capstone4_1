package com.example.capstone4_1

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class CreateQuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_quest)
        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        val makebtn = findViewById<ImageButton>(R.id.Makebtn)
        val qname = findViewById<EditText>(R.id.qname)
        val qexplain = findViewById<EditText>(R.id.qexplain)
        val questAdapter= QuestAdapter(this)

        makebtn.setOnClickListener() { view ->
            Character.customQuestList.add(Quest(R.drawable.ball,qname.text.toString(),qexplain.text.toString()))
        }

        backbtn.setOnClickListener() {
            questAdapter.notifyDataSetChanged()
            finish()
        }

    }

}