package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChangePassword : Serializable {
    @Expose
    @SerializedName("PhoneNum")
    var phoneNumber: String = ""
     @Expose
    @SerializedName("NewPassword")
    var newPassword:String = ""
     @Expose
    @SerializedName("CurrentPassword")
    var oldPassword:String = ""
}