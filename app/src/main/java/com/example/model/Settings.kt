package com.example.model

import android.content.Context
import android.content.SharedPreferences


class Settings( context: Context) {
        private  var preferences: SharedPreferences =context.getSharedPreferences("settings",
            Context.MODE_PRIVATE)
        companion object{
            lateinit var  settings:Settings
            fun getSettings(context: Context):Settings{
                if (!::settings.isInitialized){
                    settings=Settings(context)
                }
                return settings
            }

        }

    fun setEasyLevel(level:Int){
        preferences.edit().putInt("easy",level).apply()
    }
    fun getEasyLevel():Int{
        return preferences.getInt("easy",1)

    }
    fun setMediumLevel(level:Int){
        preferences.edit().putInt("medium",level).apply()
    }
    fun getMediumLevel():Int{
        return preferences.getInt("medium",1)

    }
    fun setHardLevel(level:Int){
        preferences.edit().putInt("hard",level).apply()
    }
    fun getHardLevel():Int{
        return preferences.getInt("hard",1)

    }
    fun setCurrentLevel(level:String){
        preferences.edit().putString("level",level).apply()
    }
    fun getCurrentLevel():String?{
        return preferences.getString("level","")
    }

    fun clearCurrentUser(){
            preferences.edit().putString("login",null).putString("password",null).apply()
    }
    }
