package com.skillbranch.foton.core.base

import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.skillbranch.foton.core.di.Injector


class ScopeManager : ControllerChangeHandler.ControllerChangeListener {
    override fun onChangeStarted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup, handler: ControllerChangeHandler) {
        val baseController = to as BaseController
        val mortarContext = Injector.createScreenContext(container.context, baseController.scopeKey)
        baseController.attachContext(mortarContext)
    }

    override fun onChangeCompleted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup, handler: ControllerChangeHandler) = Unit
}