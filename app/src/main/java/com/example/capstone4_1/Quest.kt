package com.example.capstone4_1

import java.util.*

class Quest(val image: Int, val name: String, val content: String, val value: Int = -1) {
    val explain: String
        get() {
            return String.format(content, value)
        }

    companion object {
        private val healthQuest = arrayListOf<Quest>(
            Quest(R.drawable.bike, "윗몸 일으키기", "윗몸 일으키기 %d번 하기", 30),
            Quest(R.drawable.bike, "팔굽혀 펴기", "팔굽혀 펴기 %d번 하기", 30),
            Quest(R.drawable.bike, "뜀걸음", "뜀걸음 %d분 하기", 10),
            Quest(R.drawable.bike, "걷기", "걷기 %d분 하기", 30)
        )

        private val languageQuest = arrayListOf<Quest>(
            Quest(R.drawable.bike, "영단어", "영단어 %d개 외우기", 20),
            Quest(R.drawable.bike, "독해", "영어문장 %d개 독해하기", 5),
            Quest(R.drawable.bike, "회화", "영어문장 %d개 말하기", 5)
        )

        private val codingQuest = arrayListOf<Quest>(
            Quest(R.drawable.bike, "백준", "백준 문제 %d개 풀기", 3)
        )

        private val defaultQuestList = arrayOf(healthQuest, languageQuest, codingQuest)

        fun getRandomList(interest: Interest): ArrayList<Quest> {
            val questList = arrayListOf<Quest>()
            var selectInterest: ArrayList<Quest> = arrayListOf<Quest>()

            when (interest) {
                Interest.HEALTH ->
                    selectInterest = healthQuest
                Interest.LANGUAGE ->
                    selectInterest = languageQuest
                Interest.CODING ->
                    selectInterest = codingQuest
            }

            val r = Random()

            for (i in 1..3) {
                questList.add(selectInterest[r.nextInt(selectInterest.size)])
            }

            for (i in 1..7) {
                val selectRandomInterest = defaultQuestList[r.nextInt(defaultQuestList.size)]

                questList.add(selectRandomInterest[r.nextInt(selectRandomInterest.size)])
            }

            return questList
        }

        fun getQuestName(): ArrayList<String>{
            val questnamelist = arrayListOf<String>()

            for (i in 0 until healthQuest.size)
                questnamelist.add(healthQuest[i].name)
            for (i in 0 until languageQuest.size)
                questnamelist.add(languageQuest[i].name)
            for (i in 0 until codingQuest.size)
                questnamelist.add(codingQuest[i].name)
//
//            for(category in defaultQuestList.indices){
//                for (quest in ){
//                    questnamelist.add()
//                }
//            }

            return questnamelist
        }

        fun getQuestImg(): ArrayList<Int> {
            val questImglist = arrayListOf<Int>()

            for (i in 0 until healthQuest.size)
                questImglist.add(healthQuest[i].image)
            for (i in 0 until languageQuest.size)
                questImglist.add(languageQuest[i].image)
            for (i in 0 until codingQuest.size)
                questImglist.add(codingQuest[i].image)

            return questImglist
        }

    }
}