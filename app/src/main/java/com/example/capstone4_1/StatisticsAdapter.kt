package com.example.capstone4_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class StatisticsAdapter(val context: Context, val statisticsList: ArrayList<Statistics>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.statistics_item, null)
            holder = ViewHolder()
            holder.genre = view.findViewById<ImageView>(R.id.img_genre)
            holder.typeName = view.findViewById<TextView>(R.id.tv_type_name)
            holder.progress = view.findViewById<TextView>(R.id.tv_progress)
            holder.nextScore = view.findViewById<TextView>(R.id.tv_next_score)
            holder.tier = view.findViewById<ImageView>(R.id.img_tier)

            view.tag = holder
        }else{
            holder = convertView.tag as ViewHolder
            view = convertView
        }
        val statistics = statisticsList[position]

        val genreId = context.resources.getIdentifier(statistics.genre, "drawable", context.packageName)
        val tierId = context.resources.getIdentifier(statistics.tier, "drawable", context.packageName)

        holder.genre?.setImageResource(genreId)
        holder.typeName?.text = statistics.title
        holder.progress?.text = statistics.count.toString()
        holder.nextScore?.text = statistics.limitCount
        holder.tier?.setImageResource(tierId)

        return view
    }

    override fun getCount(): Int {
        return statisticsList.size
    }

    override fun getItem(position: Int): Any {
        return statisticsList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    class ViewHolder {
        var genre : ImageView? = null
        var typeName: TextView? = null
        var progress: TextView? = null
        var nextScore : TextView? = null
        var tier: ImageView? = null
    }

}