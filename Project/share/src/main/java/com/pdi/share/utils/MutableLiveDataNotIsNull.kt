package com.pdi.share.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MutableLiveDataNotIsNull<T>(private val defaultValue: T) : MutableLiveData<T>() {

    override fun getValue(): T = super.getValue() ?: defaultValue

    override fun setValue(value: T) {
        if(value != null) {
            super.setValue(value)
        }
        else throw IllegalArgumentException("Not set a null value to this Type")
    }

    override fun postValue(value: T) {
        if(value != null) {
            super.postValue(value)
        }
        else throw IllegalArgumentException("Not post a null value to this Type")
    }

    fun observe(owner: LifecycleOwner, body: (T) -> Unit) {
        observe(owner, Observer<T> {
            body(it ?: defaultValue)
        })
    }

}