package com.pdi.share.extension

fun String.formatFirstLetterToUpperCase(): String {
    val firstLetter = this.substring(0, 1)
    val formatFirstLetter = firstLetter.toUpperCase()
    return this.replace(firstLetter, formatFirstLetter)
}