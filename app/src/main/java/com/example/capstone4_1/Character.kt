package com.example.capstone4_1

object Character {
    lateinit var name: String
    lateinit var gender: String
    lateinit var interest: String

    fun initialize(name: String, gender: String, interest: String) {
        this.name = name
        this.gender = gender
        this.interest = interest
    }
}