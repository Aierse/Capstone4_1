package com.example.capstone4_1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capstone4_1.Character
import com.example.capstone4_1.Quest
import com.example.capstone4_1.QuestAdapter
import com.example.capstone4_1.R
import com.google.android.material.snackbar.Snackbar


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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quest_list, container, false)
        val btnInsert = view.findViewById<Button>(R.id.btn_insert)
        val questList = view.findViewById<ListView>(R.id.questListView)
        val QuestAdapter = QuestAdapter(requireContext())



        btnInsert.setOnClickListener { view: View? ->
            val tmp_questList = questList.adapter
            val count = tmp_questList.count

//            QuestAdapter.addItem()
//            에딧 텍스트 데이터 호출위치
//            데이터 생성
            
            
            Character.questList.add(Quest(R.drawable.ball, "111111111", "33333333")) //데이터 입력 테스트코드

            Toast.makeText(requireContext(), " " + count, Toast.LENGTH_SHORT).show() //

            QuestAdapter.notifyDataSetChanged()
            Character.saveCharacter(requireContext())
        }


        //플로팅 버튼
        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "플로팅 버튼", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

            val tmp_questList = questList.adapter
            val count = tmp_questList.count

//            QuestAdapter.addItem()
//            에딧 텍스트 데이터 호출위치
//            데이터 생성

            Character.questList.add(Quest(R.drawable.ball, "111111111", "33333333")) //데이터 입력 테스트코드

            Toast.makeText(requireContext(), " " + count, Toast.LENGTH_SHORT).show() //

            QuestAdapter.notifyDataSetChanged()
            Character.saveCharacter(requireContext())
        }

        return view
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