package com.example.capstone4_1


import android.content.Context
import java.io.FileOutputStream
import java.lang.Exception
import java.time.LocalDate

// 할당된 값은 전부 스텁코드이며, 이후 필요한 스텁코드는 여기서 작성
// 실제 구동시에는 initialize 시리즈 함수를 이용하여 초기화 시켜 사용해야함
object Character {
    val filename: String = "data.json" // data file
    var name: String = "nameStub"
    var gender: Gender = Gender.MALE
    var interest: Interest = Interest.HEALTH
    var icon: Int = R.drawable.sprite_char1
    var questList = arrayListOf<Quest>()
    var current_login : LocalDate? = null //최근 로그인
    val create_time : LocalDate? = null //캐릭터 생성시점

    fun initialize(name: String, gender: Gender, interest: Interest) {
        this.name = name
        this.gender = gender
        this.interest = interest
    }

    fun initializeQuest() {
        questList = Quest.getRandomList(interest)
    }

    fun initializeStats() {

    }

    fun saveCharacter(user:Character,context: Context){

        val output :FileOutputStream

        try {
            output =context.openFileOutput(filename , Context.MODE_PRIVATE)
            output.write(JsonUtil.toJson(user, questList).toByteArray())
            output.close()

        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun loadCharacter(context: Context):String{

        val filepath = context.filesDir.toString() + "/" + filename

        return filepath
    }



}