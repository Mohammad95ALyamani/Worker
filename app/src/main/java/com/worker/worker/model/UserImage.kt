package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserImage {
    @Expose
    @SerializedName("Base64")
    var imagebase64:String = ""
}