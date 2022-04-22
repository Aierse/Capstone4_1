package com.example.capstone4_1
// 할당된 값은 전부 스텁코드이며, 이후 필요한 스텁코드는 여기서 작성
// 실제 구동시에는 initialize 시리즈 함수를 이용하여 초기화 시켜 사용해야함
object Character {
    var name: String = "nameStub"
    var gender: String = "genderstub"
    var interest: Interest = Interest.HEALTH
    var QuestList = arrayListOf<Quest>(
        Quest(R.drawable.bike, "퀘스트 이름1", "퀘스트 설명1"),
        Quest(R.drawable.face, "퀘스트 이름2", "퀘스트 설명2"),
        Quest(R.drawable.ball, "퀘스트 이름3", "퀘스트 설명3"),
        Quest(R.drawable.bike, "퀘스트 이름4", "퀘스트 설명4"),
        Quest(R.drawable.ball, "퀘스트 이름5", "퀘스트 설명5"),
        Quest(R.drawable.bike, "퀘스트 이름6", "퀘스트 설명6"),
        Quest(R.drawable.face, "퀘스트 이름7", "퀘스트 설명7"),
        Quest(R.drawable.bike, "퀘스트 이름8", "퀘스트 설명8"),
        Quest(R.drawable.face, "퀘스트 이름9", "퀘스트 설명9")
    )

    fun initialize(name: String, gender: String, interest: Interest) {
        this.name = name
        this.gender = gender
        this.interest = interest
    }

    fun initializeQuest() {

    }

    fun initializeStats() {

    }
}