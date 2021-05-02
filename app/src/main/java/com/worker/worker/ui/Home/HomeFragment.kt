package com.worker.worker.ui.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.worker.worker.R
import com.worker.worker.adapter.CategoriesAdapter
import com.worker.worker.adapter.OrdersAdapter
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.Categories
import com.worker.worker.model.Order

class HomeFragment : Fragment(), OnClickRecyclerItem, View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var categoryRecyclerView: RecyclerView? = null
    private lateinit var categoryArrayList: ArrayList<Categories>
    private var ordersArrayList: ArrayList<Order> = ArrayList()
    private var categoriesAdapter: CategoriesAdapter? = null
    lateinit var floatingActionButton2: FloatingActionButton
    lateinit var searchOrderSearchView: androidx.appcompat.widget.SearchView
    private  val TAG = "HomeFragment"
    var orderAdapter: OrdersAdapter? = null
    var selectedCategory = 0
    var searchQuery = ""
    lateinit var orderRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        findItem(root)
        categoryArrayList =  ArrayList()
        categoryArrayList.add(Categories(0,"All","","الكل"))
        homeViewModel.getCategories().observe(viewLifecycleOwner, Observer { categoriesArray ->
            if (categoriesArray != null) {

                categoryArrayList.addAll(categoriesArray.categories!!)
               setUpRecyclerViewCategories(categoryArrayList)
                Log.d(TAG, "onCreateView: ${categoriesArray.categories!!.size}")
            } else {
                Toast.makeText(activity, "failed to get Categories", Toast.LENGTH_SHORT).show()
            }

        })
        homeViewModel.getOrders(selectedCategory, searchQuery).observe(
            viewLifecycleOwner,
            Observer { response ->
                if (response != null) {
                    setUpOrderRecyclerView(response.orders!!)
                    //Log.d(TAG, "onCreateView: ${response.orders!![0].title}")

                } else {
                    Toast.makeText(activity, "failed to get Categories", Toast.LENGTH_SHORT).show()
                }

            })

        searchOrderSearchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText!!
                homeViewModel.getOrders(selectedCategory, searchQuery).observe(
                    viewLifecycleOwner,
                    Observer { response ->
                        if (response != null) {
                            setUpOrderRecyclerView(response.orders!!)
                        } else {
                            Toast.makeText(activity, "failed to get Orders", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                return true
            }

        })


        return root
    }

    private fun findItem(view: View) {
        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        floatingActionButton2 = view.findViewById(R.id.floatingActionButton2)
        orderRecyclerView = view.findViewById(R.id.orderRecyclerView)
        searchOrderSearchView = view.findViewById(R.id.searchOrderSearchView)
        floatingActionButton2.setOnClickListener(this)
    }

    private fun setUpRecyclerViewCategories(categories:ArrayList<Categories>) {
        categoriesAdapter = CategoriesAdapter(categories, activity, this)
        categoryRecyclerView?.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)


        categoryRecyclerView?.adapter = categoriesAdapter
    }

    private fun setUpOrderRecyclerView(orders: ArrayList<Order>) {
        orderAdapter = activity?.let { OrdersAdapter(orders, it, 0) }
        orderRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL,
            false
        )
        orderRecyclerView.adapter = orderAdapter
    }

    override fun onclick(o: Any) {
        selectedCategory = (o as Categories).id!!
        homeViewModel.getOrders(selectedCategory, searchQuery).observe(
            viewLifecycleOwner,
            Observer { response ->
                if (response != null) {
                    setUpOrderRecyclerView(response.orders!!)

                } else {
                    Toast.makeText(activity, "failed to get Orders", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun onClick(v: View?) {

        Navigation.findNavController(v!!).navigate(R.id.addOrderFragment)
    }
}