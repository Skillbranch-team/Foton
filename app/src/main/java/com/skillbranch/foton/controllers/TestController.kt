package com.skillbranch.foton.controllers

import android.view.View
import com.bluelinelabs.conductor.RouterTransaction
import com.skillbranch.foton.R
import com.skillbranch.foton.core.base.BaseController

class TestController :BaseController() {
    override val layoutId: Int
        get() = R.layout.controller_splash

    override fun onViewBound(view: View) {
        view.setOnClickListener { router.pushController(RouterTransaction.with(TestController())) }
    }
}