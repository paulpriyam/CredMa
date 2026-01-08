package com.example.credma.data

import androidx.room.TypeConverter
import com.example.credma.constants.BankName
import com.example.credma.constants.CardIssuer
import com.example.credma.constants.CardType

class Converters {
    @TypeConverter
    fun fromBankNameToString(bankName: BankName): String {
        return bankName.name
    }

    @TypeConverter
    fun toBankName(bankName: String): BankName {
        return when(bankName){
            "AXIS" -> BankName.AXIS
            "HDFC" -> BankName.HDFC
            "SBI" -> BankName.SBI
            "ICICI" -> BankName.ICICI
            "KOTAK" -> BankName.KOTAK
            "YES" -> BankName.YES
            "BOB" -> BankName.BOB
            "PNB" -> BankName.PNB
            else -> BankName.HDFC
        }
    }
    @TypeConverter
    fun fromCardTypeToString(cardType: CardType): String {
        return cardType.name
    }

    @TypeConverter
    fun toCardType(cardType: String): CardType {
        return CardType.valueOf(cardType)
    }
    @TypeConverter
    fun fromCardIssuerToString(cardIssuer: CardIssuer): String {
        return cardIssuer.name
    }

    @TypeConverter
    fun toCardIssuer(cardIssuer: String): CardIssuer {
        return when(cardIssuer){
            "VISA" -> CardIssuer.VISA
            "MASTERCARD" -> CardIssuer.MASTERCARD
            "RUPAY" -> CardIssuer.RUPAY
            else -> CardIssuer.VISA
        }
    }
}