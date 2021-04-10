package com.worker.worker.ui.Settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.User
import com.worker.worker.repo.UsersRepo

class SettingsViewModel : ViewModel() {
    val repo = UsersRepo()
   fun getUserInfo(token:String): MutableLiveData<User>{
       return repo.getUserInfo(token)
   }
}