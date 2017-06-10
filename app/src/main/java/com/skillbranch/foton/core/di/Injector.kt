package com.skillbranch.foton.core.di

import android.content.Context
import com.skillbranch.foton.core.base.DependencyKey
import mortar.MortarScope
import java.util.*

object Injector {
    const val SERVICE_NAME = "DAGGER_SERVICE"

    private var rootScope: MortarScope? = null

    fun getSystemService(name: String): Any? {
        return rootScope?.getService(name)
    }

    fun createScreenContext(rootContext: Context, dependencyKey: DependencyKey<*>): Context {
        var context = rootContext
        val keys = ArrayList<DependencyKey<*>>()
        var parScope: MortarScope
        keys.add(dependencyKey)

        for (key in keys.reversed()) {
            parScope = getScreenScope(context, key)
            context = parScope.createContext(context)
        }
        return context
    }

    private fun <T : Any> getScreenScope(context: Context, key: DependencyKey<T>): MortarScope {
        val parentScope = MortarScope.getScope(context)

        val childScope = parentScope.findChild(key.getScopeName())
        if (childScope != null) {
            return childScope
        }

        val parentComponent = parentScope.getService<Any>(SERVICE_NAME)
        @Suppress("UNCHECKED_CAST") val screenComponent = key.createScreenComponent(parentComponent as T)

        val builder = parentScope.buildChild().withService(SERVICE_NAME, screenComponent)

        return builder.build(key.getScopeName())
    }

    fun destroyScopeWithParent(context: Context, key: DependencyKey<*>) {
        val ms = MortarScope.getScope(context).findChild(key.getScopeName())
        ms.destroy()
    }

    fun destroyScope(context: Context) {
        val ms = MortarScope.getScope(context)
        ms.destroy()
    }

    fun <T> getDaggerComponent(context: Context): T {
        @Suppress("UNCHECKED_CAST")
        return context.getSystemService(SERVICE_NAME) as T
    }

    fun <T> getDaggerComponent(scope: MortarScope): T {
        return scope.getService<T>(SERVICE_NAME)
    }
}