package com.worker.worker.ui.Profile

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
import com.worker.worker.databinding.FragmentProfileBinding
import com.worker.worker.model.User

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var profileBinding: FragmentProfileBinding
     var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        // val user = arguments?.let { ProfileFragmentArgs.fromBundle(it).user }

        //profileBinding.user = user
        return profileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }
    fun updateUserInfo(user:User){
        viewModel.updateUserInfo(token, user).observe(viewLifecycleOwner, Observer { response->
            if (response != null){
                Toast.makeText(activity,"Success to update", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity,"failed to update",Toast.LENGTH_SHORT).show()
            }
        })
    }

}