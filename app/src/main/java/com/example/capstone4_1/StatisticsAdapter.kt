package com.example.capstone4_1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


open class StatisticsAdapter(val context: Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.statistics_item, parent, false)
            holder = ViewHolder()
            holder.genre = view.findViewById<ImageView>(R.id.img_genre)
            holder.typeName = view.findViewById<TextView>(R.id.tv_type_name)
            holder.progress = view.findViewById<TextView>(R.id.tv_progress)
            holder.nextScore = view.findViewById<TextView>(R.id.tv_next_score)
            holder.tier = view.findViewById<ImageView>(R.id.img_tier)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        Log.d("확인", "어댑터 내부 ")
//        val genreId =
//                context.resources.getIdentifier(statisticsList.genre, "drawable", context.packageName)

        val stat = Character.statisticsList[position]

        val tierId = context.resources.getIdentifier(stat.tier, "drawable", context.packageName)
        Log.d("확인", "title " + position + stat.title.toString())

        holder.genre?.setImageResource(stat.genre)
        holder.typeName?.text = stat.title//need  to fix it
        holder.progress?.text = stat.count.toString()
        holder.nextScore?.text = stat.nextLimit.toString()
        holder.tier?.setImageResource(tierId)






        return view

    }

    override fun getCount(): Int {
        return Character.statisticsList.size
    }

    override fun getItem(position: Int): Any {
        return Character.statisticsList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    class ViewHolder {
        var genre: ImageView? = null
        var typeName: TextView? = null
        var progress: TextView? = null
        var nextScore: TextView? = null
        var tier: ImageView? = null
    }

}