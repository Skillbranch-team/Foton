package com.skillbranch.foton.splash

import com.skillbranch.foton.core.base.DependencyKey
import dagger.Provides

class SplashDependencies : DependencyKey<Any>() {

    override fun createScreenComponent(parentComponent: Any): Any {
        TODO("not implemented")
    }

    @dagger.Module
    class Module {

        @Provides
        fun providePresenter() = SplashPresenter()
    }

    @dagger.Component
    interface Component {
        fun inject(view: SplashController)
    }
}