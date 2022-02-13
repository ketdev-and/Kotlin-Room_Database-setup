package com.example.room_db_setup.data

import android.app.Application
import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class userViewModel(application: Application) : AndroidViewModel(application) {
   val readAllData : LiveData<List<User>>
    private val repository : UserRepo

    init {
        val dbInstance = UserDatabase.getDB(application).userDao()
        repository = UserRepo(dbInstance)
        readAllData = repository.getUser
    }

    fun addUser(user: User) {

        viewModelScope.launch(Dispatchers.IO) {
            val saveUser = async(Dispatchers.Default) {
                repository.addUser(user)
                "user saved"
            }

            val defferd = async(Dispatchers.Default) {
               "ketrm oooo"
            }
            val value = defferd.await()
            val userSaved = saveUser.await()

            withContext(Dispatchers.Main){
                Log.e("TAG", value, )
                Log.e("TAG", userSaved)
            }



        }

    }

    fun UpdateUser(user: User) {

        viewModelScope.launch(Dispatchers.IO) {
            val updateUser = async(Dispatchers.Default) {
                repository.updateUser(user)
                "user updated"
            }
            val userSaved = updateUser.await()

            withContext(Dispatchers.Main){
                Log.e("TAG", userSaved)
            }



        }

    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            val deleteUser = async(Dispatchers.Default) {
                repository.deleteUser(user)
                "user deleted"
            }
            val userDeleted = deleteUser.await()

            withContext(Dispatchers.Main){
                Log.e("TAG", userDeleted)
            }


        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val deleteUser = async(Dispatchers.Default) {
                repository.deleteAllUser()
                "user deleted"
            }
            val userDeleted = deleteUser.await()

            withContext(Dispatchers.Main){
                Log.e("TAG", userDeleted)
            }


        }
    }
}