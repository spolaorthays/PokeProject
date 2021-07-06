package com.pdi.share.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <reified T> LifecycleOwner.observeValue(data: LiveData<T>, noinline block: (T) -> Unit) {
    data.observe(this, Observer { block.invoke(it) })
}