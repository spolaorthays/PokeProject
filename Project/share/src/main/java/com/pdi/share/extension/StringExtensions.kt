package com.pdi.share.extension

fun String.formatFirstLetterToUpperCase(): String {
    val firstLetter = this[0]
    val formatFirstLetter = firstLetter.toUpperCase()
    return formatFirstLetter+this.substring(1, this.length)
}