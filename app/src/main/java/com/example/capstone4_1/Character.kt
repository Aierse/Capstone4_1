package com.example.capstone4_1


import android.content.Context
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

// 할당된 값은 전부 스텁코드이며, 이후 필요한 스텁코드는 여기서 작성
// 실제 구동시에는 initialize 시리즈 함수를 이용하여 초기화 시켜 사용해야함
object Character {
    val filename: String = "data.json" // data file
    var name: String = "nameStub"
    var gender: Gender = Gender.MALE
    var interest: Interest = Interest.HEALTH
    var icon: Int = R.drawable.sprite_char1
    var customQuestList = arrayListOf<Quest>()
    var randomQuestList = arrayListOf<Quest>()
    var currentLogin : LocalDateTime? = null //최근 로그인
    lateinit var createTime : LocalDateTime //캐릭터 생성시점

    var questList: ArrayList<Quest>
    get() {
        return (randomQuestList + customQuestList) as ArrayList<Quest>
    }
    set(qValue) {
        var tempRandom = arrayListOf<Quest>()
        var tempCustom = arrayListOf<Quest>()

        for (i in qValue) {
            if (i.value == -1)
                tempCustom.add(i)
            else
                tempRandom.add(i)
        }

        customQuestList = tempCustom
        randomQuestList = tempRandom
    }

    fun initialize(name: String, gender: Gender, interest: Interest) {
        this.name = name
        this.gender = gender
        this.interest = interest
    }

    fun initializeQuest() {
        randomQuestList = Quest.getRandomList(interest)
    }

    fun initializeStats() {
    }

    fun survivalDays():Int?{ // return int  & must be tested,
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Period.between(this.createTime.toLocalDate(), this.currentLogin?.toLocalDate()).days
        } else {
            -1
        }
    }

    fun saveCharacter(context: Context){//save

        val user:Character = this
        val output :FileOutputStream

        try {
            output =context.openFileOutput(filename , Context.MODE_PRIVATE)
            output.write(JsonUtil.toJson(user, questList).toByteArray())
            output.close()

        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun loadCharacter(context: Context):String{// load
        val filepath = context.filesDir.toString() + "/" + filename
        val file = File(filepath)
        try {
            val reader = BufferedReader(FileReader(file))
            var result: String? = ""
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result += line
            }
            try {
                val data = JSONObject(result)
                val quests = data.getString("quests")
                val ary_quests = data.optJSONArray("quests")

                val tmp_quests=ArrayList<Quest>()

//                Log.d("log_quests",ary_quests.toString())
//                Log.d("array_length", ary_quests.length().toString() )

                for(quest in 0 until ary_quests.length()){  // need to update
                    val tmp_object = ary_quests.getJSONObject(quest)
                    val tmp_quest = Quest(tmp_object.getInt("image"),tmp_object.getString("name"),tmp_object.getString("content"), tmp_object.getInt("value"))

                    tmp_quests.add(tmp_quest)
//                    Log.d("test_value", tmp_object.getString("image").toString() )
//                    Log.d("test_value", tmp_object.getString("name") )
//                    Log.d("test_value", tmp_object.getString("content") )
                }

                this.questList = tmp_quests

                this.name =data.getString("name")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // createTime load check sdk v
                    this.createTime =  LocalDateTime.parse(data.getString("createTime") ,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS" , Locale.KOREA))
//                    Log.d("crateTime end", "create time excuted ")
//                    Log.d("createvalue", this.createTime.toString())
                }

//                Log.d("log_name",this.name.toString())

                for (i in Gender.values()) { // this.gender = data.getString(Gender!)
                    if (i.value == data.getString("gender")) {
                        this.gender = i
                        break
                    }
                }

                for (i in Interest.values()) {// this.interest = data.getString(Interest!)
                    if (i.value == data.getString("interest")) {
                        this.interest = i
                        break
                    }
                }

                this.icon = data.getInt("icon")// icon

//                Log.d("createTime",this.createTime.toString())
//                Log.d("log_gender",this.gender.toString())
//                Log.d("log_interest",this.interest.toString())
//                Log.d("log_quests",this.questList.toString())

                return ""

            }catch (e:java.lang.Exception){
                Log.d("에러",e.printStackTrace().toString())
            }
            reader.close()
        } catch (e1: FileNotFoundException) {

            Log.d("파일못찾음", e1.printStackTrace().toString())
        } catch (e2: IOException) {
            Log.d("읽기오류", e2.printStackTrace().toString())
        }
        return ""
    }
}