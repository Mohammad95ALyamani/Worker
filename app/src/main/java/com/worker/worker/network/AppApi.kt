package com.worker.worker.network

import com.worker.worker.helpers.Constants
import com.worker.worker.model.Order
import com.worker.worker.model.User
import com.worker.worker.responses.CreateOrderResponse
import com.worker.worker.responses.OrderResponse
import retrofit2.Call
import retrofit2.http.*

interface AppApi {
    @GET(Constants.getOrders)
    fun getOrders(@Query("categoryId")categoryId: Int,@Query("title") orderTitle: String): Call<OrderResponse>


    @POST(Constants.Login)
    fun loginUser(@Body user: User): Call<User>

    @POST(Constants.SignUp)
    fun signUpUser(@Body user: User): Call<User>

    @POST(Constants.createOrder)
    fun createOrder(@Body order: Order,@Header("AccessToken")token:String): Call<CreateOrderResponse>





}