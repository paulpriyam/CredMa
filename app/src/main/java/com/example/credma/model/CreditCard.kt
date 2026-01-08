package com.example.credma.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.credma.constants.BankName
import com.example.credma.constants.CardIssuer
import com.example.credma.constants.CardType
import java.util.UUID

@Entity(tableName = "credit_cards")
data class CreditCard(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val cardHolderName: String,
    val cardNumber: String,
    val cardExpiryDate: String,
    val cardCvv: String,
    val cardType: CardType,
    val bankName: BankName,
    val bankCardType: String,
    val cardIssuer: CardIssuer,
)


