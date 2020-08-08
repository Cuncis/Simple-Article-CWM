package com.cuncisboss.simplearticlecwm.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cuncisboss.simplearticlecwm.R
import com.cuncisboss.simplearticlecwm.model.User
import com.cuncisboss.simplearticlecwm.util.Constants.EMAIL_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.PASS_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.TAG
import com.cuncisboss.simplearticlecwm.util.Constants.USERNAME_PREF
import com.cuncisboss.simplearticlecwm.util.PasswordHelper
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named


class LoginFragment : Fragment() {

    private val articlePref by inject<SharedPreferences>()
    private val user by inject<User>(named("user"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: Username: ${articlePref.getString(USERNAME_PREF, "")}")
        Log.d(TAG, "onViewCreated: Email: ${articlePref.getString(EMAIL_PREF, "")}")
        Log.d(TAG, "onViewCreated: Password: ${articlePref.getString(PASS_PREF, "")}")

        btn_login.setOnClickListener {
            if (!et_email.text.toString().equals(articlePref.getString(EMAIL_PREF, ""), true)) {
                Toast.makeText(requireContext(), "This email doesn't exist in our record", Toast.LENGTH_SHORT).show()
            } else if (!PasswordHelper.isValid(
                    et_password.text.toString(),
                    articlePref.getString(PASS_PREF, "").toString())) {
                Toast.makeText(requireContext(), "Wrong password entered!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        btn_register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}