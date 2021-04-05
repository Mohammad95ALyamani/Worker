package com.worker.worker.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.databinding.ActivitySignUpBinding
import com.worker.worker.model.User
import com.worker.worker.model.UserJob

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    var job: UserJob? = null
    lateinit var signUpViewModel: SignUpViewModel
    private val TAG = "SignUpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_sign_up
        )

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        val jobsArrayList = ArrayList<UserJob>()
        val job1 = UserJob()
        job1.id = 1
        job1.name = "7dad"
        jobsArrayList.add(job1)
        val job2 = UserJob()
        job2.id = 2
        job2.name = "Najar"
        jobsArrayList.add(job2)
        val job3 = UserJob()
        job3.id = 3
        job3.name = "Engineer"
        jobsArrayList.add(job3)
        val job4 = UserJob()
        job4.id = 4
        job4.name = "Doctor"
        jobsArrayList.add(job4)
        val adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, jobsArrayList)
        binding.userJobMenu.setAdapter(adapter)


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
        if (binding.phoneTietSignUp.length() != 10) {
            binding.phoneTietSignUp.error = "Incorrect Phone number"
            return
        }
        if (binding.passwordTietSginUp.text!!.length < 8) {
            binding.passwordTietSginUp.error = "Password is too short"

            return
        }
        if (binding.confirmPasswordTietSginUp.text!!.length < 8) {
            binding.confirmPasswordTextInputLayoutSignUp.error = "Password is too short"
            return
        }
        if (binding.passwordTietSginUp.text.toString() != binding.confirmPasswordTietSginUp.text.toString()) {
            binding.passwordTietSginUp.error = "Password Doesn't Match"
            binding.confirmPasswordTietSginUp.error = "Password Doesn't Match"
            return
        }
        if (job == null) {
            binding.userJobTextLayout.error = "Job is Require! "
        }

        user.firstName = binding.firstNameTietSginUp.text.toString()
        user.lastName = binding.lastNameTietSginUp.text.toString()
        user.phoneNumber = "+962"+binding.phoneTietSignUp.text.toString()
        user.password = binding.passwordTietSginUp.text.toString()
        user.job = job
        val i = Intent(this@SignUpActivity, OTPActivity::class.java)
        i.putExtra("user", user)
        startActivity(i)


        //Todo
    }


}