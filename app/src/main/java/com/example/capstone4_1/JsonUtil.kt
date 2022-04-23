package com.example.capstone4_1
import org.json.JSONException
import org.json.JSONObject

public final class JsonUtil{
    companion object {
        fun toJson(user: Character): String {
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
    }
}
