package com.example.credma.constants

import com.example.credma.R

sealed class BankName(val name: String, val drawableResourceId: Int) {
    object HDFC : BankName("HDFC", drawableResourceId = R.drawable.ic_hdfc_bank_lgo)
    object SBI : BankName("SBI", drawableResourceId = R.drawable.ic_sbi_bank_logo)
    object ICICI : BankName("ICICI", drawableResourceId = R.drawable.ic_icici_bank_logo)
    object AXIS : BankName("AXIS", drawableResourceId = R.drawable.ic_axis_bank_logo)
    object KOTAK : BankName("KOTAK", drawableResourceId = R.drawable.ic_kotak_bank_logo)
    object YES : BankName("YES", drawableResourceId = R.drawable.ic_yes_bank_logo)
    object BOB : BankName("BOB", drawableResourceId = R.drawable.ic_bob_bank_logo)
    object PNB : BankName("PNB", drawableResourceId = R.drawable.ic_pnb_bank_logo)
}

sealed class CardIssuer(val name: String, val drawableResourceId: Int) {

    object VISA : CardIssuer("VISA", drawableResourceId = R.drawable.ic_visa_logo)
    object MASTERCARD : CardIssuer("MASTERCARD", drawableResourceId = R.drawable.ic_mastercard_logo)
    object RUPAY : CardIssuer("RUPAY", drawableResourceId = R.drawable.ic_rupay_logo)
}

enum class CardType {
    CREDIT, DEBIT
}