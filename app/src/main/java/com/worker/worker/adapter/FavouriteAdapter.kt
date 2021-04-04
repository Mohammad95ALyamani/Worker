package com.worker.worker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worker.worker.R
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.User

class FavouriteAdapter(
    private var favourits: ArrayList<User>, private var context: Context, private var onClickRecyclerItem: OnClickRecyclerItem
) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userImage: ImageView = itemView.findViewById(R.id.favouriteUserImage)
        var username: TextView = itemView.findViewById(R.id.favouriteUsername)
        var userJob: TextView = itemView.findViewById(R.id.favouriteUserJob)
        var favouriteToggle: CheckBox = itemView.findViewById(R.id.favouriteToggle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_favorite, parent, false)

        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        Glide.with(context).asBitmap().load(favourits[position].image).into(holder.userImage)
        holder.username.text = favourits[position].firstName + favourits[position].lastName
        holder.userJob.text = favourits[position].job?.name

    }

    override fun getItemCount(): Int {
        return favourits.size
    }
}