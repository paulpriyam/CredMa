package com.example.credma.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val cardId: String,
    val amount: Double,
    val date: String,
    val description: String = "",
    val category: String
)
