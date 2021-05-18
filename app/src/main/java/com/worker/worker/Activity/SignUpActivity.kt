package com.worker.worker.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.databinding.ActivitySignUpBinding
import com.worker.worker.helpers.LocalHelper
import com.worker.worker.model.User
import com.worker.worker.model.UserJob


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var jobsArrayList: ArrayList<UserJob>
    var job: UserJob? = null
    lateinit var signUpViewModel: SignUpViewModel
    private val TAG = "SignUpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_sign_up
        )
        jobsArrayList = ArrayList()
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()

        signUpViewModel.getUserJobs().observe(this, { response ->
            if (response != null) {
                Log.d(TAG, "onResume: ${response.jobs!![0].name}")
                val adapter =
                    ArrayAdapter(
                        this,
                        R.layout.support_simple_spinner_dropdown_item,
                        response.jobs!!
                    )
                jobsArrayList = response.jobs!!
                binding.userJobMenu.setAdapter(adapter)
            } else {
                Toast.makeText(this, "failed to get user jobs", Toast.LENGTH_LONG)
                    .show()
            }
        })
        binding.userJobMenu.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "selected" + jobsArrayList[position].name, Toast.LENGTH_LONG)
                .show()
            job = jobsArrayList[position]
        })
    }

    fun signUpUser(v: View) {
        val user = User()

        if (binding.firstNameTietSginUp.text!!.length < 3) {
            binding.firstNameTietSginUp.error = "This Field is Require"
            return
        }
        if (binding.lastNameTietSginUp.text!!.length < 3) {
            binding.lastNameTietSginUp.error = "This Field is Require"
            return
        }
        if (binding.phoneTietSignUp.length() != 9) {
            binding.phoneTietSignUp.error = "Incorrect Phone number"
            return
        }
        if (binding.passwordTietSginUp.text!!.length < 8) {
            binding.passwordTextInputLayoutSignUp.error = "Password is too short"

            return
        }
        if (binding.confirmPasswordTietSginUp.text!!.length < 8) {
            binding.confirmPasswordTextInputLayoutSignUp.error = "Password is too short"
            return
        }
        if (binding.passwordTietSginUp.text.toString() != binding.confirmPasswordTietSginUp.text.toString()) {
            //binding.passwordTietSginUp.error = "Password Doesn't Match"
            binding.confirmPasswordTextInputLayoutSignUp.error = "Password Doesn't Match"
            return
        }
        if (job == null) {
            binding.userJobTextLayout.error = "Job is Require! "
        }
        signUpViewModel.checkUser(binding.phoneTietSignUp.text.toString()).observe(this, {
            it
            if (it != null) {
                if (it.result?.get(0)!!) {
                    binding.phoneTietSignUp.error = "this phone is used with other user"
                } else {
                    user.firstName = binding.firstNameTietSginUp.text.toString()
                    user.lastName = binding.lastNameTietSginUp.text.toString()
                    val num = "+962" + binding.phoneTietSignUp.text.toString()
                    user.phoneNumber = binding.phoneTietSignUp.text.toString()
                    user.password = binding.passwordTietSginUp.text.toString()
                    user.job = job
                    val i = Intent(this@SignUpActivity, OTPActivity::class.java)
                    i.putExtra("user", user)
                    i.putExtra("num", num)
                    startActivity(i)
                }
            } else {
                Toast.makeText(this,"Try Again Later",Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalHelper.onAttach(newBase!!))
    }


}