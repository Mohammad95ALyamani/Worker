package com.worker.worker.ui.Favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.FollowersResponse

class FavouriteViewModel : ViewModel() {
        val repo  = UsersRepo()

    fun getFollowers(token:String):MutableLiveData<FollowersResponse>{
        return repo.getFollowers(token)
    }

}