package com.example.capstone4_1

import android.util.Log

object StatisticsList {
        private var tierType = arrayOf<String>("bronze_tier", "silver_tier", "gold_tier")
        private var cutLine = arrayOf<Int>(100, 1000, 10000)


        var testGenre = arrayOf<Int>()
        var testTitle = arrayOf<String>()
        var testLimit = arrayOf<Int>()
        var testTier = arrayOf<String>()

        fun initializeGenre(){ testGenre = Quest.getQuestImg().toTypedArray() }

        fun initializeTitle(){ testTitle = Quest.getQuestName().toTypedArray() }

        fun initializeCutlineAndTier(test: Array<Int>) {
                val resultCutline = arrayListOf<Int>()
                val resultTier = arrayListOf<String>()

                for (i in 0 until test.size) {
                        if (test[i] <= cutLine[0]) {
                                resultCutline.add(cutLine[0])
                                resultTier.add(tierType[0])

                        } else if (cutLine[0] <= test[i] && test[i] <= cutLine[1]) {
                                resultCutline.add(cutLine[1])
                                resultTier.add(tierType[1])
                        } else {
                                resultCutline.add(cutLine[2])
                                resultTier.add(tierType[2])
                        }
                }
                testLimit = resultCutline.toTypedArray()
                testTier = resultTier.toTypedArray()
        }

        @JvmName("getStatisticsList1")
        fun getStatisticsList(): ArrayList<Statistics> {
                for (i in 0 until testTitle.size) {
                        Character.statisticsList.add(Statistics(testGenre[i], testTitle[i], Character.userCount[i], testLimit[i], testTier[i]))
                }
                Log.d("확인", "유저카운터 크기  "+ Character.userCount.size)
                return Character.statisticsList
        }

        fun resetList() {
                Log.d("확인", "리셋 슈웃: ")
                Character.statisticsList.clear()
        }

        fun addCount(quest: Quest) {
                var qn = quest.name
                var qv = quest.value
                for (i in testTitle.indices) {
                        if (testTitle[i] == quest.name)
                                Character.userCount[i] += quest.value
                }
        }


        fun setUserCount(){
                //처음 유저카운터가 없으면 0으로 초기화
                if (Character.userCount.isEmpty()){
                        Character.userCount = Array(testTitle.size,{0})
                }



        }
}


