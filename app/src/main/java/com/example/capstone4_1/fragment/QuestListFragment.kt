package com.example.capstone4_1.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capstone4_1.CreateQuestActivity
import com.example.capstone4_1.QuestAdapter
import com.example.capstone4_1.R
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
        val nextQuestTime = view.findViewById<TextView>(R.id.nextQuestTime)


        //퀘스트 생성 버튼 클릭 리스너
        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(intent)
        }

        nextQuestTime.setText(remainQuestTime())

        return view
    }

    fun remainQuestTime(): String {
        val remainQuestTime = "00:00:00"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nowTime = LocalTime.parse("07:57:44")
            val resetTime = LocalTime.parse("06:00:00")
//            val nowTime = LocalTime.now()
            var duration = Duration.between(resetTime,nowTime).seconds
            Log.d("Duration :","${duration.toString()}" )
            if (duration < 0) {
                duration = -1 * duration

                var hour = duration / 3600

                if (hour.toInt() == 24) {
                    hour = 0
                    Log.d("fffff", "hour: " + hour)
                }

                duration %= 3600
                val minutes = duration / 60
                duration %= 60
                val seconds = duration

                val remainQuestTime =
                    " " + hour.toString() + ":" + minutes.toString() + ":" + seconds.toString()

                return remainQuestTime

            } else {
                duration = 86400- duration
                var hour = duration/3600

                Log.d("hours :" ,"${hour.toString()}")

                duration %= 3600
                val minutes = duration / 60
                Log.d("hours :" ,"${minutes.toString()}")
                duration %= 60
                val seconds = duration
                Log.d("seconds :" , "${seconds.toString()}")

                val remainQuestTime =
                    " " + hour.toString() + ":" + minutes.toString() + ":" + seconds.toString()

                return remainQuestTime
            }
        }
        return ""
    }


    override fun onResume() {
        super.onResume()

        view?.findViewById<ListView>(R.id.questListView)?.adapter =
            QuestAdapter(requireContext())
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