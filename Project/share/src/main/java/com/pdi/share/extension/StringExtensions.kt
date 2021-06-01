package com.pdi.share.extension

fun String.formatFirstLetterToUpperCase(): String {
    return this[0].toUpperCase() + this.substring(1, this.length)
}