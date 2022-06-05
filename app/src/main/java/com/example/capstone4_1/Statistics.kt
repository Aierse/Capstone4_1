package com.example.capstone4_1

import android.util.Log

class Statistics(val count: Int,val titleNum: Int) {
    val genre: String = Character.interest.toString().lowercase()
    val title: ArrayList<String> = initializeTitle()
    var limit: Int = initializeLimit()
    var tier: String = initializeTier()
    val tierList: Array<String> = arrayOf("bronze_tier", "silver_tier", "gold_tier")
    val limitList: Array<Int> = arrayOf(100,1000,10000)

    companion object {
//        fun addElement(addCount: Int, maxSize: Int ){
//            for (i in 0..maxSize) {
//                statisticsList.add(i, Statistics(addCount, titleNum = i))
//            }
//        }
        val statisticsList: MutableList<Statistics> = mutableListOf<Statistics>(
            Statistics(200,1)
        )

    }

    fun initializeTitle(): ArrayList<String> {
        val test = ArrayList<String>()
        Log.d("확인", "Character.questList.size "+Character.questList.size)

        for (j in 0..Character.questList.size) {
            test.add(Character.questList[j].name)
            Log.d("확인", " "+ test[j] +test.size )
        }
        for (i in 0..test.size) {
            Log.d("확인", "배열 내용 확인 "+test[i])
        }
        test.distinct()
        return test
    }


    fun initializeLimit(): Int {
        return if (count <= limitList[0]) {
            limitList[0]
        } else if (limitList[0] < count && count <= limitList[1]) {
            limitList[1]
        } else {
            limitList[2]
        }
        Log.d("확인", "initializeLimit: 리미트 도달")
    }


    fun initializeTier(): String {
        return if (count <= limitList[0]) {
            tierList[0]
        } else if (limitList[0] < count && count <= limitList[1]) {
            tierList[1]
        } else {
            tierList[2]
        }
        Log.d("확인", "initializeLimit: 티어 도달")
    }
}
