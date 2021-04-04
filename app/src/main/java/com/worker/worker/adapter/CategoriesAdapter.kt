package com.worker.worker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worker.worker.R
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.Categories
import kotlin.collections.ArrayList

class CategoriesAdapter(
    private var categoriesArrayList: ArrayList<Categories>?, private var context: Context?, private var onClickItem: OnClickRecyclerItem?
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName!!.text = categoriesArrayList!![position].name
        Glide.with(context!!).asBitmap().load(categoriesArrayList!![position].image).into(holder.categoryImage!!)

        holder.itemView.setOnClickListener(View.OnClickListener {
            selectedPosition = position
            onClickItem!!.onclick(categoriesArrayList!![position])
            notifyDataSetChanged()
        })

        holder.categoriesBackground?.isSelected = selectedPosition == position
        holder.categoriesBackground?.isPressed =  selectedPosition == position
        holder.categoriesBackground?.isActivated =  selectedPosition == position
    }

    override fun getItemCount(): Int {
        return categoriesArrayList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName: TextView? = null
        var categoryImage: ImageView? = null
        var categoriesBackground: ConstraintLayout? = null
        init {
            categoryName = itemView.findViewById(R.id.categoryNameTextView)
            categoryImage = itemView.findViewById(R.id.categoryImageView)
            categoriesBackground = itemView.findViewById(R.id.categoriesBackground)
        }
    }
}