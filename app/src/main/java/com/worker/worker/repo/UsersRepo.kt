package com.worker.worker.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.ReportRequest
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import com.worker.worker.network.Builder
import com.worker.worker.responses.FollowersResponse
import com.worker.worker.responses.ImageResponse
import com.worker.worker.responses.ReportResponse
import com.worker.worker.responses.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepo {
    private val TAG = "UsersRepo"
    lateinit var followersMutable: MutableLiveData<FollowersResponse>
    lateinit var followUserMutable: MutableLiveData<CustomResponse>
    lateinit var reportResponseMutable: MutableLiveData<ReportResponse>
    lateinit var reportUserMutable: MutableLiveData<CustomResponse>
    fun getFollowing(token: String): MutableLiveData<FollowersResponse> {
        followersMutable = MutableLiveData()

        val call: Call<FollowersResponse> = Builder.service.getFollowing(token)
        call.enqueue(object : Callback<FollowersResponse> {
            override fun onResponse(
                call: Call<FollowersResponse>,
                response: Response<FollowersResponse>
            ) {
                if (response.body()!!.status==200) {
                    followersMutable.value = response.body()
                } else {
                    followersMutable.value = null
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                followersMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followersMutable
    }

    fun getFollower(token: String): MutableLiveData<FollowersResponse> {
        followersMutable = MutableLiveData()

        val call: Call<FollowersResponse> = Builder.service.getFollowing(token)
        call.enqueue(object : Callback<FollowersResponse> {
            override fun onResponse(
                call: Call<FollowersResponse>,
                response: Response<FollowersResponse>
            ) {

                if (response.body()!!.status==200) {
                    followersMutable.value = response.body()
                } else {
                    followersMutable.value = null
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                followersMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followersMutable
    }


    fun followUser(token: String, user:User): MutableLiveData<CustomResponse> {
        followUserMutable = MutableLiveData()
        val call = Builder.service.followUser(token, user)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    followUserMutable.value = response.body()
                } else {
                    followUserMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                followUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followUserMutable
    }

    fun unFollowUser(token: String, user:User): MutableLiveData<CustomResponse> {
        followUserMutable = MutableLiveData()
        val call = Builder.service.unFollowUser(token, user.id)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    followUserMutable.value = response.body()
                } else {
                    followUserMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                followUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followUserMutable
    }

    fun getReports(token: String): MutableLiveData<ReportResponse> {
        reportResponseMutable = MutableLiveData()
        val call = Builder.service.getReports(token)
        call.enqueue(object : Callback<ReportResponse> {
            override fun onResponse(
                call: Call<ReportResponse>,
                response: Response<ReportResponse>
            ) {
                if (response.body()!!.status==200) {
                    reportResponseMutable.value = response.body()
                } else {
                    reportResponseMutable.value = null
                }
            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                reportResponseMutable.value = null
            }

        })

        return reportResponseMutable
    }

    fun reportUser(token: String, reportRequest: ReportRequest): MutableLiveData<CustomResponse> {
        reportUserMutable = MutableLiveData()
        val call = Builder.service.reportUser(token, reportRequest)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    reportUserMutable.value = response.body()
                } else {
                    reportUserMutable.value = null
                }

            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                reportUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return reportUserMutable
    }

    lateinit var userMutable: MutableLiveData<UserResponse>

    fun getUserInfo(token: String): MutableLiveData<UserResponse> {
        userMutable = MutableLiveData()
        val call = Builder.service.getUserInfo(token)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.body()!!.status==200) {
                    userMutable.value = response.body()
                } else {
                    userMutable.value = null
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return userMutable
    }

    lateinit var updateUserInfo: MutableLiveData<CustomResponse>
    fun updateUserInfo(token: String, user: User): MutableLiveData<CustomResponse> {
        updateUserInfo = MutableLiveData()

        val call = Builder.service.updateUserInfo(token, user)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    updateUserInfo.value = response.body()
                } else {
                    updateUserInfo.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                updateUserInfo.value = null
            }

        })
        return updateUserInfo
    }

    lateinit var updateUserImageMutable: MutableLiveData<ImageResponse>

    fun updateImage(token: String, image: UserImage): MutableLiveData<ImageResponse> {
        updateUserImageMutable = MutableLiveData()

        val call = Builder.service.updateUserImage(token, image)
        call.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(
                call: Call<ImageResponse>,
                response: Response<ImageResponse>
            ) {
                if (response.body()!!.status==200) {
                    updateUserImageMutable.value = response.body()
                } else {
                    updateUserImageMutable.value = null
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                updateUserImageMutable.value = null
            }
        })


        return updateUserImageMutable

    }
    
    lateinit var isFollowingMutable: MutableLiveData<CustomResponse>
    
    fun isFollowing(token: String , userId:Int):MutableLiveData<CustomResponse>{
        isFollowingMutable = MutableLiveData()
        
        val call = Builder.service.isFollowing(token, userId)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status == 200) {
                    isFollowingMutable.value = response.body()
                } else {
                    isFollowingMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                isFollowingMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        
        return isFollowingMutable
    }
}