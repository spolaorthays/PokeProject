package com.pdi.share.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pdi.share.R

class CustomCardView @JvmOverloads constructor(
        context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
): RelativeLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.support_simple_spinner_dropdown_item, this)
    }


}