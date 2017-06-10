package com.skillbranch.foton.splash

import android.view.View
import com.skillbranch.foton.R
import com.skillbranch.foton.core.base.BaseController
import com.skillbranch.foton.core.base.DependencyKey
import com.skillbranch.foton.core.di.Injector
import javax.inject.Inject

class SplashController : BaseController(), SplashView {
    @Inject
    lateinit var presenter: SplashPresenter

    override val layoutId: Int = R.layout.controller_splash

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.takeView(this)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.dropView(this)
    }

    override val scopeKey: DependencyKey<*> = SplashDependencies()

    override fun onContextAttached() {
        Injector.getDaggerComponent<String>(context)
    }
}