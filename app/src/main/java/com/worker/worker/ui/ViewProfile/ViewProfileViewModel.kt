package com.worker.worker.ui.ViewProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.repo.UsersRepo

class ViewProfileViewModel : ViewModel() {
    val repo = UsersRepo()
    fun followUser(token:String,user:User):MutableLiveData<CustomResponse>{
        return repo.followUser(token, user)
    }

    fun unFollowUser(token:String,user:User):MutableLiveData<CustomResponse>{
        return repo.followUser(token, user)
    }
}