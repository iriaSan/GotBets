package com.iriasan.gotbets.core

import android.text.InputFilter
import android.text.Spanned
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.regex.Pattern

/** USAGE -> ('a'..'z').randomString(6) */
fun ClosedRange<Char>.randomString(lenght: Int) =
    (1..lenght)
        .map { (Random().nextInt(endInclusive.toInt() - start.toInt()) + start.toInt()).toChar() }
        .joinToString("")


class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {

    var mPattern: Pattern =
        Pattern.compile("[0-9]{0," + (digitsBeforeZero - 2) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {

        val matcher = mPattern.matcher(dest)
        return if (!matcher.matches()) "" else null
    }
}

fun setupCurrencyEditText(currencySymbol: String): Locale? {
    when (currencySymbol) {
        "\$" -> {
            Locale.setDefault(Locale("es", "AR"))
        }
        "€" -> {
            Locale.setDefault(Locale("es", "ES"))
        }
    }
    return Locale.getDefault()
}

fun cleanCurrencyByDouble(cost: String?): Double? {
    var priceValue = cost

    if (priceValue.isNullOrEmpty() || priceValue.isNullOrBlank()) {
        priceValue = "0.0"
    }
    var costMod: String? = priceValue
    if (costMod != null) {

        if (costMod.contains(".")) {
            costMod = costMod.replace(".", "")
        }

        if (costMod.contains(",")) {
            costMod = costMod.replace(",", ".")
        }
//        val re = Regex("[^0-9.,]+")
        val re = Regex("[^0-9.,]+")
        costMod = re.replace(costMod, "")

        return costMod.toDouble()
    }

    return null
}

fun doubleCurrencyValueToSpanishDecimal(budgetCostValue: Double?): String? {
    val df = DecimalFormat("#,##0.00")
    df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
    val n = budgetCostValue
    return df.format(n)
}


