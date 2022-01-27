package com.diazp.catchit.helper

import java.text.NumberFormat
import java.util.*

class Helper {
    fun toRupiah(int: Int) : String? {
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(int)
    }
}