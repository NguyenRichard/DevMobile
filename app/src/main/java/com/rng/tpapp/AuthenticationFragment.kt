package com.rng.tpapp

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_authentication.view.*

class AuthenticationFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_authentication, container, false)

        view.button_signup.setOnClickListener { signUp() }
        view.button_login.setOnClickListener { login() }

        if(PreferenceManager.getDefaultSharedPreferences(context).getString(SHARED_PREF_TOKEN_KEY,"") != ""){
            startMainActivity()
        }

        return view
    }

    private fun signUp(){
        findNavController().navigate(R.id.action_authenticationFragment_to_signupFragment)
    }

    private fun login(){
        findNavController().navigate(R.id.action_authenticationFragment_to_loginFragment)
    }

    private fun startMainActivity(){
        val intent = Intent(this.context,MainActivity::class.java)
        startActivity(intent)

    }
}

