package com.example.capstone4_1.fragment

import android.content.Intent
import android.os.Bundle
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
        val questList = view.findViewById<ListView>(R.id.questListView)
        val questAdapter = QuestAdapter(requireContext())


        //플로팅 버튼
        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            //데이터 입력 테스트코드
            Character.questList.add(Quest(R.drawable.ball,"asd","asd"))

            //커스텀 퀘스트 이동
            val intent: Intent = Intent(activity, quest_custom::class.java)
            startActivity (intent);

//            Toast.makeText(context,"Name : " + name + "Explain : " + explain, Toast.LENGTH_SHORT ).show()

            questList.adapter = QuestAdapter(requireContext())
            questAdapter.notifyDataSetChanged()
        }

        return view
//            val view: View = LayoutInflater.from(context).inflate(R.layout.quest_item, container,false)
//
//            val viewHolder = QuestAdapter.ViewHolder().apply {
//                name = view.findViewById<TextView>(R.id.quest_name)
//                explain = view.findViewById<TextView>(R.id.quest_explain)
//            imageView = view.findViewById<ImageView>(R.id.image)
//            }
//
//            viewHolder.apply {
//                name.setText(Character.customQuest.name)
//                explain.setText(Character.customQuest.explain)
//            }
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