package com.skillbranch.foton.core.base

abstract class DependencyKey<in T : Any> {
    abstract fun createScreenComponent(parentComponent: T): Any

    fun getScopeName(): String = this::class.java.name

    override fun equals(other: Any?): Boolean = this.javaClass.name == other?.javaClass?.name

    override fun hashCode(): Int {
        return super.hashCode()
    }
}