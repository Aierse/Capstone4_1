package com.example.capstone4_1


import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.time.LocalDateTime

// 할당된 값은 전부 스텁코드이며, 이후 필요한 스텁코드는 여기서 작성
// 실제 구동시에는 initialize 시리즈 함수를 이용하여 초기화 시켜 사용해야함
object Character {
    val filename: String = "data.json" // data file
    var name: String = "nameStub"
    var gender: Gender = Gender.MALE
    var interest: Interest = Interest.HEALTH
    var icon: Int = R.drawable.sprite_char1
    var questList = arrayListOf<Quest>()
    var currentLogin : LocalDate? = null //최근 로그인
    lateinit var createTime : LocalDateTime //캐릭터 생성시점

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

    fun saveCharacter(context: Context){

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

    fun loadCharacter(context: Context):String{

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

                Log.d("log_quests",ary_quests.toString())
                Log.d("array_length", ary_quests.length().toString() )

                for(quest in 0 until ary_quests.length()){  // need to update
                    val tmp_quest = ary_quests.getJSONObject(quest)

                    Log.d("test_value", tmp_quest.getString("image").toString() )
                    Log.d("test_value", tmp_quest.getString("name") )
                    Log.d("test_value", tmp_quest.getString("explain") )

                }

                Log.d("log_quests",this.questList.toString())

                this.name =data.getString("name")
                Log.d("log_name",this.name.toString())
                this.gender = enumValueOf(data.getString("gender"))//need to fix
                Log.d("log_gender",this.gender.toString())
                this.interest = enumValueOf(data.getString("interest"))
                Log.d("log_interest",this.interest.toString())

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