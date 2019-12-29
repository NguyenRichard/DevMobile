package com.rng.tpapp

import android.app.Application
import network.Api

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        Api.INSTANCE = Api(this)
    }
}