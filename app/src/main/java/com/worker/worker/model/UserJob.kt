package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.sql.StatementEvent

class UserJob: Serializable {
    @Expose
    @SerializedName("Id")
    var id: Int = 0
    @Expose
    @SerializedName("Name")
    var name: String = ""
    @Expose
    @SerializedName("Description")
    var description: String = ""
    @Expose
    @SerializedName("Shortcut")
    var shortcut: String = ""
    @Expose
    @SerializedName("ImageUrl")
    var image:String = ""

    override fun toString(): String {
        return name
    }
}