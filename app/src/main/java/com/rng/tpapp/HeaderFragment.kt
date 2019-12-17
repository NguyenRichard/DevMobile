package com.rng.tpapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_header.*
import kotlinx.android.synthetic.main.fragment_header.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import network.Api


class HeaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_header, container, false)

        view.avatar.setOnClickListener{startUserInfoActivity()}

        return view
    }

    private val coroutineScope = MainScope()

    override fun onResume() {
        super.onResume()
        coroutineScope.launch {
            val userInfo = Api.INSTANCE.userService.getInfo().body()
            var greetName = "Hi "+userInfo?.firstName+" "+userInfo?.lastName;
            user_name.text = greetName
            Glide.with(this@HeaderFragment).load(userInfo?.avatar?:"http://goo.gl/gEgYUd").apply(RequestOptions.circleCropTransform()).into(avatar)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }


    private fun startUserInfoActivity(){
        val intent = Intent(activity,UserInfoActivity::class.java)
        startActivity(intent)
    }
}
