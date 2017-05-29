package com.skillbranch.foton.controllers.base

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.skillbranch.foton.ActionBarProvider


abstract class BaseController protected constructor(args: Bundle? = null) : Controller(args) {

    // Note: This is just a quick demo of how an ActionBar *can* be accessed, not necessarily how it *should*
    // be accessed. In a production app, this would use Dagger instead. TODO
    protected fun getActionBar(): ActionBar? {
        val actionBarProvider = activity as ActionBarProvider
        return actionBarProvider.getSupportActionBar()
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup): View =
            inflater.inflate(layoutId, container, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        onViewBound(view)
        return view
    }

    abstract protected val layoutId: Int

    protected fun onViewBound(view: View) = Unit

    override fun onAttach(@NonNull view: View) {
        setTitle()
        super.onAttach(view)
    }

    protected fun setTitle() {
        var parentController = parentController
        while (parentController != null) {
            if (parentController is BaseController && parentController.getTitle() != null)
                return

            parentController = parentController.parentController
        }

        val title = getTitle()
        val actionBar = getActionBar()
        if (title != null && actionBar != null) {
            actionBar.title = title
        }
    }

    protected fun getTitle(): String? {
        return null
    }
}