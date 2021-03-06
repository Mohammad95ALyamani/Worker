package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Categories(id: Int, name: String, image: String, arName: String) : Serializable{
    @Expose
    @SerializedName("Id")
    var id: Int? = 0

    @Expose
    @SerializedName("Name")
    var name: String? = null

    @Expose
    @SerializedName("Shortcut")
    var shortcut: String? = null

    @Expose
    @SerializedName("ArabicName")
    var arName: String? = null

    @Expose
    @SerializedName("ImageUrl")
    var image: String? = null

    init {
        this.id = id
        this.name = name
        this.image = image
        this.arName = arName
    }


    override fun toString(): String {
        return name.toString()
    }


}