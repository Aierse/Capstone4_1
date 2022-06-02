package com.example.capstone4_1

val healthTitle : Array<String> = arrayOf("팔굽혀 펴기", "풀업" , "뜀걸음")
val studyTitle : Array<String> = arrayOf("영단어 외우기", "단어의 뜻 말하기" , "독서")
val codingTitle : Array<String> = arrayOf("백준 문제 풀기", "코딩 영상 보기", "코딩 실습 ")
val tierList: Array<String> = arrayOf("bronze_tier", "silver_tier", "gold_tier")
val limitList: Array<Int> = arrayOf(100,1000,10000)

//<a href="https://www.flaticon.com/kr/free-icons/" title="헬스장 아이콘">헬스장 아이콘  제작자: Payungkead - Flaticon</a>
data class Statistics(val count: Int ) {
    val genre: String = Character.interest.toString().lowercase()
    var title: Array<String> = initializeTitle()
    var limit: Int = initializeLimit()
    var tier: String = initializeTier()

    fun initializeTitle(): Array<String> {
        val i = this.genre
        return if (i == "health") {
            healthTitle
        } else if (i == "study") {
            studyTitle
        } else {
            codingTitle
        }
    }

    fun initializeLimit(): Int {
        return if (count <= limitList[0]) {
            100
        } else if (limitList[0] < count && count <= limitList[1]) {
            1000
        } else {
            10000
        }
    }

    fun initializeTier(): String {
        return if (count <= limitList[0]) {
            tierList[0]
        } else if (limitList[0] < count && count <= limitList[1]) {
            tierList[1]
        } else {
            tierList[2]
        }
    }



    companion object{
        var statisticsList = arrayListOf<Statistics>(
            Statistics(200)
            ,Statistics(1000)
            ,Statistics(50)
        )
    }
}
