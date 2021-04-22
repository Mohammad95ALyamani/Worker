package com.worker.worker.ui.Settings

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
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
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.worker.worker.Activity.LoginActivity
import com.worker.worker.R
import com.worker.worker.SplashActivity
import com.worker.worker.databinding.FragmentSettingsBinding
import com.worker.worker.helpers.Constants
import com.worker.worker.helpers.LocalHelper
import com.worker.worker.model.ChangePassword
import com.worker.worker.model.User


class SettingsFragment : Fragment() {


    private lateinit var viewModel: SettingsViewModel
    lateinit var settingsBinding: FragmentSettingsBinding
    lateinit var user: User
    var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!

        settingsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        if (token.isNotEmpty()) {
            settingsBinding.isLoggedIn = true
            viewModel.getUserInfo(token).observe(viewLifecycleOwner, Observer { response ->
                if (response != null) {
                    user = response.result!![0]
                    activity?.let {
                        Glide.with(it).asBitmap().load(response.result!![0].image)
                            .into(settingsBinding.imageView4)
                    }
                    settingsBinding.user = user
                } else {
                    Toast.makeText(activity, "failed to get User", Toast.LENGTH_SHORT).show()
                }


            })
        } else {
            settingsBinding.isLoggedIn = false
        }


        settingsBinding.securityCard.setOnClickListener { v ->
            if (token.isNotEmpty()) {
                val change = ChangePassword()
                change.phoneNumber = user.phoneNumber
                val des =
                    SettingsFragmentDirections.actionNavigationSettingsToChangePasswordFragment(
                        change
                    )
                Navigation.findNavController(v).navigate(des)
            }

        }


        settingsBinding.languageCardView.setOnClickListener {


            val alertDialog = AlertDialog.Builder(requireContext())

            //set title for alert dialog
            alertDialog.setTitle(getString(R.string.change_lang))
            //set message for alert dialog
            alertDialog.setMessage(getString(R.string.are_you_sure))
            alertDialog.setIcon(R.drawable.ic_change_language)

            //performing positive action
            alertDialog.setPositiveButton("English") { _, _ ->
                updateViews("en")

                addToSharedPreference(
                    activity,
                    "lang",
                    Constants.ENGLISH_LANG
                )
                startActivity(Intent(activity, SplashActivity::class.java))
                activity?.finish()

            }

            alertDialog.setNegativeButton("عربي") { _, _ ->
                updateViews("ar")

                addToSharedPreference(
                    activity,
                    "lang",
                    Constants.ARABIC_LANG
                )
                startActivity(Intent(activity, SplashActivity::class.java))
                activity?.finish()

            }


            // Create the AlertDialog

            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()


        }
        settingsBinding.profileCardView.setOnClickListener(View.OnClickListener
        { v ->
            if (token.isNotEmpty()) {
                val des = SettingsFragmentDirections.actionNavigationSettingsToProfileFragment(user)
                Navigation.findNavController(v).navigate(des)
            } else {
                val i = Intent(activity, LoginActivity::class.java)
                startActivity(i)
            }

        })


        settingsBinding.logoutCard.setOnClickListener(View.OnClickListener {

            saveUserToken("")
            saveUserId(0)
            startActivity(activity?.intent)

        })
        return settingsBinding.root
    }


    private fun updateViews(languageCode: String) {
        val context: Context = LocalHelper.setLocale(
            activity?.applicationContext!!,
            languageCode
        )
        val resources: Resources = context.resources
    }


    private fun addToSharedPreference(context: Context?, key: String?, value: Int) {
        val sharedPreference: SharedPreferences =
            context?.getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)!!
        val editor = sharedPreference.edit()
        editor.putInt(key, value)
        editor.apply()
    }
    private fun saveUserId(token: Int) {
        val sharedPreference =
            activity?.getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference?.edit()
        editor?.putInt("id", token)
        editor?.apply()
    }

    private fun saveUserToken(token: String) {
        val sharedPreference =
            activity?.getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference?.edit()
        editor?.putString("token", token)
        editor?.apply()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


}