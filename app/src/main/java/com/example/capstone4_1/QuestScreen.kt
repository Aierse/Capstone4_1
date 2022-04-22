package com.example.capstone4_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class Quest(val image: Int, val Qname: String, val explain: String)

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

//밑 데이터 리스트는 테스트 용임
var QuestList = arrayListOf<Quest>(
    Quest(R.drawable.bike, "퀘스트 이름1", "퀘스트 설명1"),
    Quest(R.drawable.face, "퀘스트 이름2", "퀘스트 설명2"),
    Quest(R.drawable.ball, "퀘스트 이름3", "퀘스트 설명3"),
    Quest(R.drawable.bike, "퀘스트 이름4", "퀘스트 설명4"),
    Quest(R.drawable.ball, "퀘스트 이름5", "퀘스트 설명5"),
    Quest(R.drawable.bike, "퀘스트 이름6", "퀘스트 설명6"),
    Quest(R.drawable.face, "퀘스트 이름7", "퀘스트 설명7"),
    Quest(R.drawable.bike, "퀘스트 이름8", "퀘스트 설명8"),
    Quest(R.drawable.face, "퀘스트 이름9", "퀘스트 설명9")
)


class QuestScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_screen)

        val Adapter = QuestAdapter(this, QuestList)
        val listview = findViewById<ListView>(R.id.listview)

        listview.adapter = Adapter //어답터 리스트뷰에 적용

        listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectItem = parent.getItemAtPosition(position) as Quest
                toast(selectItem.Qname + selectItem.explain + Adapter.count.toString())

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
