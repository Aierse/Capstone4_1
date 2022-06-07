package com.example.capstone4_1

class Statistics(var genre: Int, var title: String, var limit: Array<Int>, var count: Int = 0) {
    val nextLimit: Int?
    get() {
        for (i in limit) {
            if (count < i)
                return i
        }

        return null
    }

    val tier: String
        get() {
            if (limit[2] <= count)
                return "gold_tier"
            if (limit[1] <= count)
                return "silver_tier"
            if (limit[0] <= count)
                return "bronze_tier"

            return "unranked_tier"
        }

    companion object {
        private val healthStatistics = arrayListOf<Statistics>(
            Statistics(R.drawable.ball, "윗몸 일으키기", arrayOf<Int>(200, 1000, 10000)),
            Statistics(R.drawable.bike, "팔굽혀 펴기", arrayOf<Int>(200, 1000, 10000)),
            Statistics(R.drawable.bike, "뜀걸음", arrayOf<Int>(200, 1000, 10000)),
            Statistics(R.drawable.bike, "걷기", arrayOf<Int>(200, 1000, 10000))
        )

        private val languageStatistics = arrayListOf<Statistics>(
            Statistics(R.drawable.bike, "영단어", arrayOf<Int>(200, 1000, 10000)),
            Statistics(R.drawable.bike, "독해", arrayOf<Int>(200, 1000, 10000)),
            Statistics(R.drawable.bike, "회화", arrayOf<Int>(200, 1000, 10000))
        )

        private val codingStatistics = arrayListOf<Statistics>(
            Statistics(R.drawable.bike, "백준", arrayOf<Int>(200, 1000, 10000))
        )

        val statisticsList = healthStatistics + languageStatistics + codingStatistics
    }
}

data class QuestCountRecord(var questname: String, var playcount: Int)