package com.example.capstone4_1


import org.json.JSONException
import org.json.JSONObject

public final class JsonUtil {
    companion object {
        fun toJson(user: Character): String { //object to json  // stub
            try {
                val json = JSONObject()
                json.put("name", user.name)
                json.put("gender", user.gender)
                json.put("attention", user.interest)

                return json.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return ""
        }
        fun toJson(user: Character, questlist: ArrayList<Quest>): String { //quest = guestjson this fun not used if you need to use this fun you might test fun
            try {
                val host_json = JSONObject()
                host_json.put("name", user.name)
                host_json.put("gender", user.gender.value)
                host_json.put("attention", user.interest.value)

                val quests_ary = org.json.JSONArray()

                for(quests in questlist){  // questlist in quests
                    var quest_json = JSONObject()
                    quest_json.put("image",quests.image)
                    quest_json.put("name",quests.name )
                    quest_json.put("explain",quests.explain)
                    quests_ary.put(quest_json)
                }

                host_json.put("quests", quests_ary)
                return host_json.toString()

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return ""
        }

    }

}
