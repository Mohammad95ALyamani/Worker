package com.worker.worker.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.User
import com.worker.worker.network.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel : ViewModel() {
    private lateinit var userLiveData: MutableLiveData<User>
    private  val TAG = "LoginActivityViewModel"
    fun loginUser (user: User): MutableLiveData<User>{
        userLiveData = MutableLiveData()
       val call:Call<User> =  Builder.service.loginUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userLiveData.value = response.body()
                } else {
                    userLiveData.value = null
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userLiveData.value = null
                Log.d(TAG, "onFailure: "+ t.message)
            }

        })



        return userLiveData
    }
}