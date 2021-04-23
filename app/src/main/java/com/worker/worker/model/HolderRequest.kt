package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HolderRequest {
    @Expose
    @SerializedName("UserId")
    var id: Int = 0
     @Expose
    @SerializedName("OrderId")
    var order:Int = 0
}