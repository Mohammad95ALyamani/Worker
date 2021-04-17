package com.worker.worker.ui.Favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.Activity.LoginActivity
import com.worker.worker.R
import com.worker.worker.adapter.FavouriteAdapter
import com.worker.worker.databinding.FragmentFavouriteBinding
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.User

class FavouriteFragment : Fragment(), OnClickRecyclerItem {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteBinding: FragmentFavouriteBinding
    lateinit var adapter: FavouriteAdapter
    var users = ArrayList<User>()
    var token = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)
        favouriteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)

        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        favouriteBinding.isLoggedIn = token.isNotEmpty()



        return favouriteBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (token.isNotEmpty()) {
            favouriteViewModel.getFollowers(token)
                .observe(viewLifecycleOwner, Observer { respones ->
                    if (respones != null) {
                        setUpFavouriteRecyclerView(respones.followers!!)
                    } else {
                        Toast.makeText(activity, "failed To get Orders", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        favouriteBinding.goToLogin.setOnClickListener(View.OnClickListener {
            val i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
        })
    }

    private fun setUpFavouriteRecyclerView(users: ArrayList<User>) {
        adapter = activity?.let { FavouriteAdapter(users, it, this) }!!
        favouriteBinding.favouriteRecyclerView.adapter = adapter
    }

    override fun onclick(o: Any) {
        val user = o as User
        favouriteViewModel.unFollow(token, o).observe(viewLifecycleOwner, Observer { respose ->
            if (respose != null) {
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
}