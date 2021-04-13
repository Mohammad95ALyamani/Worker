package com.worker.worker.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.network.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckUserViewModel : ViewModel() {

    lateinit var checkUserMutable:MutableLiveData<CustomResponse>
    private  val TAG = "CheckUserViewModel"
    fun checkUser(phone:String):MutableLiveData<CustomResponse>{
        checkUserMutable = MutableLiveData()
        val call = Builder.service.checkUser(phone)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    checkUserMutable.value = response.body()
                } else {
                    checkUserMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                checkUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })


        return checkUserMutable
    }
}