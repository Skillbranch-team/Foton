package com.skillbranch.foton.core.base

import mortar.Presenter
import mortar.bundler.BundleService

open class BasePresenter<V : BaseView> : Presenter<V>() {
    override fun extractBundleService(view: V): BundleService {
        return BundleService.getBundleService(view.context)
    }
}