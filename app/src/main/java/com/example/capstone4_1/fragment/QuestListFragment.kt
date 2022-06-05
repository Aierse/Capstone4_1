package com.example.capstone4_1.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.capstone4_1.*
import java.time.Duration
import java.time.LocalTime


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quest_list, container, false)
        val intent = Intent(requireContext(), CreateQuestActivity::class.java)
        val rootView = view.findViewById<ListView>(R.id.questListView)
        val questAdapter = QuestAdapter(requireContext())
        val doQuest = view.findViewById<TextView>(R.id.doQuest)
        doQuest.setText(Character.doingQuetstCount.toString() + " / 3")

        //퀘스트 생성 버튼 클릭 리스너
        val fab = view.findViewById<Button>(R.id.fab)
        fab.setOnClickListener {
            startActivity(intent)
        }

        rootView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialog = AlertDialog.Builder(requireContext())
                val selectQuest = parent.getItemAtPosition(position) as Quest

                //다이얼로그 이름
                dialog.setTitle(selectQuest.name)
                //다이얼로그 설명
                dialog.setMessage(" 이 퀘스트를 해치우셨나용? ")

                // 확인 버튼 클릭시 동작할 것들!!!
                dialog.setPositiveButton("완료") { dialogInterface, i ->
                    Toast.makeText(
                        requireContext(), selectQuest.name + "\n 퀘스트를 완료하셨습니다.", Toast.LENGTH_SHORT
                    ).show()

                    if (Character.questList[position].value == -1) {
                        val realPosition = position - Character.randomQuestList.count()
                        Character.customQuestList.removeAt(realPosition)
                    } else {
                        Character.randomQuestList.removeAt(position)
                        Character.doingQuetstCount++
                    }
                    rootView.adapter = QuestAdapter(requireContext())
                    questAdapter.notifyDataSetChanged()
                    doQuest.setText(Character.doingQuetstCount.toString() + " / 3")
                }
                dialog.setNegativeButton("취소") { dialogInterface, i -> }
                dialog.show()
            }

        return view
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

    override fun onResume() {
        super.onResume()
        //남은시간 계산
        val nextQuestTime = view?.findViewById<TextView>(R.id.nextQuestTime)
        nextQuestTime?.setText(remainQuestTime())

        //퀘스트 진행 현황
//        val CurrentQuest = view?.findViewById<TextView>(R.id.doQuest)
//        CurrentQuest?.setText(Character.doingQuetstCount.toString() + " / 3")

        //리스트뷰 갱신
        view?.findViewById<ListView>(R.id.questListView)?.adapter = QuestAdapter(requireContext())
        QuestAdapter(requireContext()).notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}