package com.worker.worker.ui.Favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.adapter.FavouriteAdapter
import com.worker.worker.databinding.FragmentFavouriteBinding
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.User
import com.worker.worker.model.UserJob

class FavouriteFragment : Fragment(), OnClickRecyclerItem {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteBinding: FragmentFavouriteBinding
    lateinit var adapter: FavouriteAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
                ViewModelProvider(this).get(FavouriteViewModel::class.java)
       favouriteBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite,container,false)
        val user = User()
        user.firstName = "Ahmad"
        user.lastName = "Nofal"
        user.phoneNumber = "0786878242"
        user.image = "https://www.washingtonpost.com/pbox.php?url=http://www.washingtonpost.com/news/speaking-of-science/wp-content/uploads/sites/36/2014/09/fortyfaces250.gif&w=1484&op=resize&opt=1&filter=antialias&t=20170517"
        val job = UserJob()
        job.name = "ENG"
        user.job = job
        val users = ArrayList<User>()
        users.add(0,user)
        setUpFavouriteRecyclerView(users)
        return favouriteBinding.root
    }

    fun setUpFavouriteRecyclerView(users:ArrayList<User>){
        adapter = activity?.let { FavouriteAdapter(users, it,this) }!!
        favouriteBinding.favouriteRecyclerView.adapter = adapter
    }

    override fun onclick(o: Any) {
        TODO("Not yet implemented")
    }
}