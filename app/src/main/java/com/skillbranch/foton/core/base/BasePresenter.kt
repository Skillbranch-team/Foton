package com.skillbranch.foton.core.base

import mortar.Presenter
import mortar.bundler.BundleService
import javax.inject.Inject

open class BasePresenter<V : BaseView> : Presenter<V>() {
    @Inject
    lateinit var view: V

    override fun extractBundleService(view: V): BundleService {
        return BundleService.getBundleService(view.context)
    }
}