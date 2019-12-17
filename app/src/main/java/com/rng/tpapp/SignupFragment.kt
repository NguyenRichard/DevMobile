package com.rng.tpapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import network.Api

class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        view.signup_button.setOnClickListener { signUp() }

        return view
    }

    private val coroutineScope = MainScope()

    private fun signUp() {
        val firstname = text_input_firstname.text.toString()
        val lastname = text_input_lastname.text.toString()
        val email = text_input_email.text.toString()
        val password = text_input_password.text.toString()
        val password_confirmation = text_input_password_confirmation.text.toString()
        if(firstname == "" || lastname == "" || email == ""
            || password == "" || password_confirmation == ""){
            Toast.makeText(this.context,"You must fill all fields",Toast.LENGTH_SHORT)
            return
        }
        if(password.compareTo(password_confirmation) != 0){
            Toast.makeText(this.context,"The password confirmation does not match password.",Toast.LENGTH_SHORT)
            return
        }
        val signUpForm = SignupForm(firstname,lastname,email,password,password_confirmation)
        coroutineScope.launch{
            val response = Api.INSTANCE.userService.signup(signUpForm)
            if(response.isSuccessful){
                Toast.makeText(this@SignupFragment.context,"You have signed up!",Toast.LENGTH_SHORT)
                startAuthenticationActivity()
            }else{
                Toast.makeText(this@SignupFragment.context,"The posting to the server has failed: "+response.errorBody(),Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun startAuthenticationActivity() {
        val intent = Intent(context,AuthenticationActivity::class.java)
        startActivity(intent)
    }

}
