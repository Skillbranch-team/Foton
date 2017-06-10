package com.skillbranch.foton.core

import android.app.Application
import com.skillbranch.foton.core.di.Injector

class App : Application() {

    override fun getSystemService(name: String): Any {
        val service = Injector.getSystemService(name)
        return service ?: super.getSystemService(name)
    }

    companion object {
        lateinit var INSTANCE: App
    }
}