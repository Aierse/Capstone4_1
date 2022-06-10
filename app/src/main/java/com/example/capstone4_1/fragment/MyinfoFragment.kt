package com.example.capstone4_1.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capstone4_1.Character
import com.example.capstone4_1.R
import com.example.capstone4_1.StatisticsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyinfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyinfoFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_myinfo, container, false)

        val characterImg = view.findViewById<ImageView>(R.id.myCharacter)
        val name = view.findViewById<TextView>(R.id.myInfoName)
        val gender = view.findViewById<TextView>(R.id.myInfoGender)
        val interest = view.findViewById<TextView>(R.id.myInfoInterest)
        val hpbar = view.findViewById<RatingBar>(R.id.hp_Bar)

        characterImg.setImageResource(Character.icon)
        name.append("이름:${Character.name}")
        gender.append("성별:${Character.gender.value}")
        interest.append("관심:${Character.interest.value}")

        hpbar.rating = Character.hp

        Log.d("확인", "임포 프래그먼트 도착 ")
        val listView = view.findViewById<ListView>(R.id.statisticsListView)

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
         * @return A new instance of fragment MyinfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                MyinfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}

