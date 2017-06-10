package com.skillbranch.foton.root

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.skillbranch.foton.R
import com.skillbranch.foton.splash.SplashController
import com.skillbranch.foton.core.base.ScopeManager
import kotlinx.android.synthetic.main.activity_root.*
import mortar.bundler.BundleServiceRunner

class RootActivity : AppCompatActivity(), ActionBarProvider {
    private lateinit var router: Router

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setSupportActionBar(toolbar)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(SplashController()))

        router.addChangeListener(ScopeManager())



        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState)
//        MortarScope.getDaggerComponent<RootComponent>(this).inject(this)

    }



    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState)
    }

    override fun getSupportActionBar(): ActionBar = super.getSupportActionBar()!!

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }
}