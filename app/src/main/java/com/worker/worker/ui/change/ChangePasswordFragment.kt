package com.worker.worker.ui.change

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.databinding.ChangePasswordFragmentBinding
import com.worker.worker.model.ChangePassword
import com.worker.worker.model.CustomResponse
import com.worker.worker.network.Builder
import com.worker.worker.ui.Profile.ProfileFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }

    private lateinit var viewModel: ChangePasswordViewModel
    lateinit var binding: ChangePasswordFragmentBinding
    lateinit var change: ChangePassword
    private val TAG = "ChangePasswordFragment"
    lateinit var token:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!

        binding =
            DataBindingUtil.inflate(inflater, R.layout.change_password_fragment, container, false)
        change = arguments?.let { ChangePasswordFragmentArgs.fromBundle(it).change }!!
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

        binding.doneChange.setOnClickListener(View.OnClickListener {
            doneChanging()
        })
    }

    fun doneChanging() {
        if (binding.oldET.text.toString().length < 8) {
            binding.oldPassword.error = "Password Too Short"
            return
        }
        if (binding.newET.text.toString().length < 8) {
            binding.newPassword.error = "Password Too short"
            return
        }
        if (binding.reNewET.text.toString().length < 8) {
            binding.reNewPassword.error = "Password Too Short"
            return
        }
        if (binding.reNewET.text.toString() != binding.newET.text.toString()) {
            binding.newPassword.error = "Password Doesn't Match!"
            return
        }
        change.newPassword = binding.newET.text.toString()
        change.oldPassword = binding.oldET.text.toString()

        val call = Builder.service.changeUserPassword(token,change)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    activity!!.onBackPressed()
                    Toast.makeText(activity, "Password changed successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(activity, "Old Password is incorrect ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Toast.makeText(activity, "Old Password is incorrect ", Toast.LENGTH_SHORT)
                        .show()
            }

        })
    }

}