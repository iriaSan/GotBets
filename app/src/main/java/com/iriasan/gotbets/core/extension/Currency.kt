package com.iriasan.gotbets.core.extension

import java.util.Currency
import java.util.Locale

// fun Currency.displayCurrencyInfoForLocale(locale: Locale) {
//    System.out.println("Locale: " + locale.getDisplayName())
//    val currency = Currency.getInstance(locale)
//    System.out.println("Currency Code: " + currency.getCurrencyCode())
//    System.out.println("Symbol: " + currency.getSymbol())
//    System.out.println("Default Fraction Digits: " + currency.getDefaultFractionDigits())
//    println()
// }

fun Currency.symbol(locale: Locale): String? = Currency.getInstance(locale).symbol
fun Currency.symbol(): String? = Currency.getInstance(Locale.getDefault()).symbol
