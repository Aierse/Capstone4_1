package com.example.capstone4_1.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.capstone4_1.Quest
import com.example.capstone4_1.QuestAdapter
import com.example.capstone4_1.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestlistviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestlistviewFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.listview_questlistview, container, false)
        val rootView = view.findViewById<ListView>(R.id.questListView)


        rootView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialog = AlertDialog.Builder(requireContext())

                val selectQuest = parent.getItemAtPosition(position) as Quest

                //다이얼로그 이름
                dialog.setTitle(selectQuest.name)
                //다이얼로그 설명
                dialog.setMessage(selectQuest.explain)
                // 확인 버튼 클릭시 동작할 것들!!!
                dialog.setPositiveButton("확인") { dialogInterface, i ->  }
                dialog.show()
            }

        rootView.adapter = QuestAdapter(requireContext())

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QlistviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestlistviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}