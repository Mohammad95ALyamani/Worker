package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {
    @Expose
    @SerializedName("Id")
    var id: Int = 0

    @Expose
    @SerializedName("FirstName")
    var firstName: String = ""

    @Expose
    @SerializedName("LastName")
    var lastName: String = ""

    @Expose
    @SerializedName("Password")

    var password: String = ""

    @Expose
    @SerializedName("PhoneNum")
    var phoneNumber: String = ""

    @Expose
    @SerializedName("AccessToken")

    var token: String = ""

    @Expose
    @SerializedName("Image")
    var image: String = ""

    @Expose
    @SerializedName("Job")
    var job: UserJob? = null


}