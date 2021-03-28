package com.worker.worker.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.R
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.adapter.CategoriesAdapter
import com.worker.worker.model.Categories
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), OnClickRecyclerItem {

    private lateinit var homeViewModel: HomeViewModel
    private var categoryRecyclerView: RecyclerView? = null
    private var categoryArrayList: ArrayList<Categories> = ArrayList()
    private var categoriesAdapter: CategoriesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        categoryArrayList?.add(0,Categories(1,"7dad","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        categoryArrayList?.add(1,Categories(2,"Najar","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        categoryArrayList?.add(2,Categories(3,"Mosarji","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        categoryArrayList?.add(2,Categories(4,"Mosarji","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        categoryArrayList?.add(2,Categories(5,"Mosarji","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        categoryArrayList?.add(2,Categories(6,"Mosarji","https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"))
        findItem(root)
        setUpRecyclerViewCategories()
        return root
    }

    private fun findItem(view: View) {
        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        categoryRecyclerView?.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
    }
    private fun setUpRecyclerViewCategories(){
        categoriesAdapter = CategoriesAdapter(categoryArrayList,activity,this)
        categoryRecyclerView?.adapter = categoriesAdapter
    }

    override fun onclick(o: Any) {

    }
}