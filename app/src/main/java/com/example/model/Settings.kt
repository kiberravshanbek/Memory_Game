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
        return preferences.getInt("easy",0)

    }
    fun setMediumLevel(level:Int){
        preferences.edit().putInt("easy",level).apply()
    }
    fun getMediumLevel():Int{
        return preferences.getInt("easy",0)

    }
    fun setHardLevel(level:Int){
        preferences.edit().putInt("easy",level).apply()
    }
    fun getHardLevel():Int{
        return preferences.getInt("easy",0)

    }

        fun clearCurrentUser(){
            preferences.edit().putString("login",null).putString("password",null).apply()
        }
    }
