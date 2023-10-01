package com.example.model

class CustomList(val level:Level) {
    private lateinit var list:ArrayList<ImageModel>
    fun loadData(level: Level):ArrayList<ImageModel>{
        list=ArrayList()
        list.add(ImageModel(2))


        return list
    }
}