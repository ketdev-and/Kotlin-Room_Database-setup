package com.example.room_db_setup.data

import android.service.autofill.UserData
import androidx.lifecycle.LiveData

class UserRepo(private val userDoa : UserDao){
    val getUser : LiveData<List<User>> = userDoa.readAllData()
    suspend fun addUser(user:User){
        userDoa.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDoa.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDoa.deleteUser(user)
    }

    suspend fun  deleteAllUser(){
        userDoa.deleteAllUser()
    }
}