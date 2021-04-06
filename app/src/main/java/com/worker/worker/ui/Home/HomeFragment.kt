package com.worker.worker.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), OnClickRecyclerItem, View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var categoryRecyclerView: RecyclerView? = null
    private var categoryArrayList: ArrayList<Categories> = ArrayList()
    private var ordersArrayList: ArrayList<Order> = ArrayList()
    private var categoriesAdapter: CategoriesAdapter? = null
    lateinit var floatingActionButton2: FloatingActionButton
    var orderAdapter: OrdersAdapter? = null
    lateinit var orderRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        categoryArrayList?.add(
            0,
            Categories(
                1,
                "7dad",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList?.add(
            1,
            Categories(
                2,
                "Najar",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList?.add(
            2,
            Categories(
                3,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList?.add(
            2,
            Categories(
                4,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList?.add(
            2,
            Categories(
                5,
                "Mosarji",
                "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
            )
        )
        categoryArrayList?.add(
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
//        homeViewModel.getCategories().observe(viewLifecycleOwner, Observer { categoriesArray ->
//            categoryArrayList.addAll(categoriesArray)
//            categoriesAdapter!!.notifyDataSetChanged()
//        })
//        homeViewModel.getOrders(0, "").observe(viewLifecycleOwner, Observer {
//
//        })
        return root
    }

    private fun findItem(view: View) {
        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        floatingActionButton2 = view.findViewById(R.id.floatingActionButton2)
        orderRecyclerView = view.findViewById(R.id.orderRecyclerView)
        floatingActionButton2.setOnClickListener(this)
    }

    private fun setUpRecyclerViewCategories() {
        categoriesAdapter = CategoriesAdapter(categoryArrayList, activity, this)
        categoryRecyclerView?.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)


        categoryRecyclerView?.adapter = categoriesAdapter
    }

    private fun setUpOrderRecyclerView() {
        orderAdapter = activity?.let { OrdersAdapter(ordersArrayList, it) }
        orderRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        orderRecyclerView.adapter = orderAdapter
    }

    override fun onclick(o: Any) {
        //Todo
    }

    override fun onClick(v: View?) {

        Navigation.findNavController(v!!).navigate(R.id.addOrderFragment)
    }
}