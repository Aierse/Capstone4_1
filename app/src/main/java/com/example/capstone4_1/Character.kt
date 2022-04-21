package com.example.capstone4_1

object Character {
    lateinit var name: String
    lateinit var gender: String
    lateinit var interest: Interest

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