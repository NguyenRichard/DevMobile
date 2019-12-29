package com.rng.tpapp

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import network.Api


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.button_login.setOnClickListener { login() }

        return view
    }

    private val coroutineScope = MainScope()

    private fun login(){
        val email = text_input_email.text.toString()
        val password = text_input_password.text.toString()

        if(email == "" || password == ""){
            Toast.makeText(this.context,"You must enter an email and a password.",Toast.LENGTH_SHORT).show()
            return
        }

        val loginForm = LoginForm(email,password)

        coroutineScope.launch{
            val response = Api.INSTANCE.userService.login(loginForm)
            if( response.isSuccessful){
                PreferenceManager.getDefaultSharedPreferences(this@LoginFragment.context).edit{
                    putString(SHARED_PREF_TOKEN_KEY, response.body()?.token)
                }
                startMainActivity()
            }
            else
            {
                Toast.makeText(this@LoginFragment.context,"The posting to the server has failed: "+response.errorBody(),Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this.context,MainActivity::class.java)
        startActivity(intent)

    }

}
