package com.example.capstone4_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView



class QuestAdapter(val context: Context, val QuestList: ArrayList<Quest>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.quest_list, null)

        val image = view.findViewById<ImageView>(R.id.image)
        val Qname = view.findViewById<TextView>(R.id.quest_name)
        val explain = view.findViewById<TextView>(R.id.quest_explain)

        val quest = QuestList[position]

        image.setImageResource(quest.image)
        Qname.text = quest.Qname
        explain.text = quest.explain

        return view
    }

    //리스트 갯수
    override fun getCount(): Int {
        return QuestList.size
    }

    override fun getItem(position: Int): Any {
        return QuestList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}




