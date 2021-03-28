package com.worker.worker.ui.Favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
                ViewModelProvider(this).get(FavouriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favourite, container, false)

        return root
    }
}