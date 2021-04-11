package com.worker.worker.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
import com.worker.worker.model.User

class HomeFragment : Fragment(), OnClickRecyclerItem, View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var categoryRecyclerView: RecyclerView? = null
    private var categoryArrayList: ArrayList<Categories> = ArrayList()
    private var ordersArrayList: ArrayList<Order> = ArrayList()
    private var categoriesAdapter: CategoriesAdapter? = null
    lateinit var floatingActionButton2: FloatingActionButton
    lateinit var searchOrderSearchView: SearchView
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
        categoryArrayList.add(
            0,
            Categories(
                1,
                "7dad",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList.add(
            1,
            Categories(
                2,
                "Najar",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList.add(
            2,
            Categories(
                3,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList.add(
            2,
            Categories(
                4,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList.add(
            2,
            Categories(
                5,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList.add(
            2,
            Categories(
                6,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        val order = Order()
        val user = User()
        order.title = "We need Smith"
        order.description =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
        order.price = 30.0
        val lat = 31.9565783
        val lng = 35.945695099999966
        order.lat = lat.toString()
        order.lng = lng.toString()
        user.firstName = "Ahmad"
        user.lastName = "Nofal"
        val cate = Categories(
            1,
            "7dad",
            "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
        )
        order.categories = cate
        order.publisher = user
        ordersArrayList.add(0, order)
        findItem(root)
        setUpRecyclerViewCategories()
        setUpOrderRecyclerView()
        homeViewModel.getCategories().observe(viewLifecycleOwner, Observer { categoriesArray ->
            if (categoriesArray != null) {
                categoryArrayList = categoriesArray.categories!!
                categoriesAdapter!!.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "failed to get Categories", Toast.LENGTH_SHORT).show()
            }

        })
        homeViewModel.getOrders(selectedCategory, searchQuery).observe(
            viewLifecycleOwner,
            Observer {

            })
        searchOrderSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText!!
                homeViewModel.getOrders(selectedCategory, searchQuery).observe(
                    viewLifecycleOwner,
                    Observer { response ->
                        if (response != null) {
                            ordersArrayList = response
                            orderAdapter!!.notifyDataSetChanged()
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

    private fun setUpRecyclerViewCategories() {
        categoriesAdapter = CategoriesAdapter(categoryArrayList, activity, this)
        categoryRecyclerView?.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)


        categoryRecyclerView?.adapter = categoriesAdapter
    }

    private fun setUpOrderRecyclerView() {
        orderAdapter = activity?.let { OrdersAdapter(ordersArrayList, it, 0) }
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
                    ordersArrayList = response
                    orderAdapter!!.notifyDataSetChanged()

                } else {
                    Toast.makeText(activity, "failed to get Orders", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun onClick(v: View?) {

        Navigation.findNavController(v!!).navigate(R.id.addOrderFragment)
    }
}