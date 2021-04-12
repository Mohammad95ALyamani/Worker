package com.worker.worker.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.sql.StatementEvent

class UserJob: Serializable {
    @SerializedName("Id")
    var id: Int = 0
    @SerializedName("Name")
    var name: String = ""
    @SerializedName("Description")
    var description: String = ""
    @SerializedName("Shortcut")
    var shortcut: String = ""
    @SerializedName("ImageUrl")
    var image:String = ""
    override fun toString(): String {
        return name
    }
}