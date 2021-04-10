package com.worker.worker.responses

import com.worker.worker.model.Categories

class CategoriesResponse {
    var status: Int = 0
    var message: String = ""
    var categories: ArrayList<Categories>? = null
}