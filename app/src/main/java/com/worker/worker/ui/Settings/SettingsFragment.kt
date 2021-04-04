package com.worker.worker.ui.Settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R

class SettingsFragment : Fragment() {


    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        val privacyPolicyCardView: CardView = root.findViewById(R.id.privacyPolicyCardView)
        privacyPolicyCardView.setOnClickListener{
            openDialog(root,R.layout.dialog_privacy_policy,R.id.privacyPolicyCardView,"Privacy Policy")
        }

        val languageCardView: CardView = root.findViewById(R.id.languageCardView)
        languageCardView.setOnClickListener{
            openDialog(root,R.layout.dialog_language,R.id.languageCardView,"Change Language")
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun openDialog(view: View,layout:Int ,id: Int,title:String) {
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