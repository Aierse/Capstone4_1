package com.example.capstone4_1

data class Statistics (val genre: String, val title: String, val count: Int, val limitCount: String, val tier: String){
    companion object{
        var statisticsList = arrayListOf<Statistics>(
            Statistics("ball","팔굽혀 펴기",500,"/1000","silver_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier"),
            Statistics("ball", "뜀걸음" ,10,"/100","bronze_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier"),
            Statistics("ball", "풀업" ,5000,"/10000","gold_tier")
        )
    }
}