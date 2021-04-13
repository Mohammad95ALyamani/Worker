package com.worker.worker.ui.Settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.User
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.UserResponse

class SettingsViewModel : ViewModel() {
    val repo = UsersRepo()
   fun getUserInfo(token:String): MutableLiveData<UserResponse>{
       return repo.getUserInfo(token)
   }
}