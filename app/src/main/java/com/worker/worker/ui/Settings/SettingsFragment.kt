package com.worker.worker.ui.Settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.worker.worker.Activity.LoginActivity
import com.worker.worker.R
import com.worker.worker.databinding.FragmentSettingsBinding
import com.worker.worker.model.User

class SettingsFragment : Fragment() {


    private lateinit var viewModel: SettingsViewModel
    lateinit var settingsBinding: FragmentSettingsBinding
    lateinit var user: User
    var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        settingsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container,false)


        settingsBinding.privacyPolicyCardView.setOnClickListener{
            openDialog(settingsBinding.root,R.layout.dialog_privacy_policy,R.id.privacyPolicyCardView,"Privacy Policy")
        }


        settingsBinding.languageCardView.setOnClickListener{
            openDialog(settingsBinding.root,R.layout.dialog_language,R.id.languageCardView,"Change Language")
        }
       settingsBinding.profileCardView.setOnClickListener(View.OnClickListener { v->
           if (token.isNotEmpty()){
               val des = SettingsFragmentDirections.actionNavigationSettingsToProfileFragment(user)
               Navigation.findNavController(v).navigate(des)
           }else{
                val i = Intent(activity,LoginActivity::class.java)
               startActivity(i)
           }

       })
        return settingsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        if (token.isNotEmpty()){
            settingsBinding.isLoggedIn = true
             viewModel.getUserInfo(token).observe(viewLifecycleOwner, Observer { response ->
            if (response != null){
                user = response
            }else{
                Toast.makeText(activity,"failed to get User",Toast.LENGTH_SHORT).show()
            }


        })
        }else{
            settingsBinding.isLoggedIn = false
        }



    }

    private fun openDialog(view: View, layout:Int, id: Int, title:String) {
        var cardView: CardView = view.findViewById(id)
        val mDialogView = LayoutInflater.from(activity).inflate(
            layout,
            null
        );

        val mBuilder = AlertDialog.Builder(activity)
            .setView(mDialogView)
            .setTitle(title)

        val mAlertDialog = mBuilder.show()
    }
}