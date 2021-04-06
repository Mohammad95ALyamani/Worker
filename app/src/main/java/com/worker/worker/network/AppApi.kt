package com.worker.worker.network

import com.worker.worker.helpers.Constants
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.responses.CreateOrderResponse
import retrofit2.Call
import retrofit2.http.*

interface AppApi {
    @GET(Constants.getOrders)
    fun getOrders(@Query("categoryId")categoryId: Int,@Query("title") orderTitle: String): Call<ArrayList<Order>>


    @POST(Constants.Login)
    fun loginUser(@Body user: User): Call<User>

    @POST(Constants.SignUp)
    fun signUpUser(@Body user: User): Call<User>

    @POST(Constants.createOrder)
    fun createOrder(@Body order: Order,@Header("AccessToken")token:String): Call<CreateOrderResponse>

    @GET(Constants.getJobCategory)
    fun getCategories(): Call<ArrayList<Categories>>

    @POST()
    fun getUserInfo(@Header("AccessToken")token: String):Call<User>

    @PUT()
    fun updateUserInfo(@Header("AccessToken")token: String,@Body user: User): Call<CustomResponse>
    @PUT
    fun changeUserPassword(@Header("AccessToken") token: String, @Body user: User):Call<CustomResponse>

    @POST()
    fun followUser(@Header("AccessToken") token: String,@Body user: User): Call<CustomResponse>

    @GET()
    fun getFollowers(@Header("AccessToken") token: String,@Query("userId")id:Int)

    @GET()
    fun getHistory(@Header("AccessToken") token: String):Call<ArrayList<Order>>

    @PUT()
    fun updateOrderInfo(@Header("AccessToken") token: String):Call<CustomResponse>


}