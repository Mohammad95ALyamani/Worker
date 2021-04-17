package com.worker.worker.ui.ViewProfile

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
import com.worker.worker.R
import com.worker.worker.databinding.FragmentViewProfileBinding
import com.worker.worker.model.User

class ViewProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ViewProfileFragment()
    }

    var isChecked = false

    lateinit var viewProfileBinding: FragmentViewProfileBinding
    private lateinit var viewModel: ViewProfileViewModel
    var token = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_profile, container, false)
        val user = arguments?.let { ViewProfileFragmentArgs.fromBundle(it).user }
        viewProfileBinding.user = user
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!


        return viewProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewProfileViewModel::class.java)
        val user = arguments?.let { ViewProfileFragmentArgs.fromBundle(it).user }
        if (token.isNotEmpty()) {
            viewModel.isFollowing(token, user!!.id)
                .observe(viewLifecycleOwner, Observer { respnse ->
                    if (respnse != null) {
                        if (respnse.result != null) {
                            isChecked = respnse.result!![0]
                            viewProfileBinding.favouriteToggle.isChecked = isChecked
                        }

                    } else {
                        Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        viewProfileBinding.favouriteToggle.setOnClickListener(View.OnClickListener {

            if (viewProfileBinding.favouriteToggle.isChecked) {
                if (user != null) {
                    followUser(user)
                }
            } else {
                user?.let { unFollowUser(it) }
            }

        })
        viewProfileBinding.user = user
    }

    private fun followUser(user: User) {
        viewModel.followUser(token, user).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                isChecked = true
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            } else {
                //viewProfileBinding.favouriteToggle.isChecked = false
            }
        })
    }

    private fun unFollowUser(user: User) {
        viewModel.unFollowUser(token, user).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                isChecked =false
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            } else {
                // viewProfileBinding.favouriteToggle.isChecked = true
            }
        })
    }

}