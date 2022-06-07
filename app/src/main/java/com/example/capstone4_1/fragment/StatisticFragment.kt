package com.example.capstone4_1.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.capstone4_1.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.statistics_list, container, false)
        val listView = view.findViewById<ListView>(R.id.statisticsListView)

        Log.d("확인", "어댑터 입구 도착 ")



        if (StatisticsList.testTitle.size <= Character.statisticsList.size)
            StatisticsList.resetList()

        if (StatisticsList.testGenre.isEmpty()){
            StatisticsList.initializeTitle()
            StatisticsList.initializeGenre()
            Log.d("확인", "userCount.size "+ Character.userCount.isEmpty())
            StatisticsList.setUserCount()
            Log.d("확인", "userCount.size "+ Character.userCount.isEmpty())
        }
        StatisticsList.initializeCutlineAndTier(Character.userCount)
        StatisticsList.getStatisticsList()


        Log.d("확인", "명령어 끝부분에 도착 ")

        listView.adapter = StatisticsAdapter(requireContext())

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}