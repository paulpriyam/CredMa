package com.example.credma

import android.app.Application
import com.example.credma.data.CardDatabase
import com.example.credma.repository.CardRepository
import com.example.credma.repository.TransactionRepository
import kotlin.getValue

class CredMaApplication: Application() {

    val database by lazy { CardDatabase.getDatabase(this) }
    val cardRepository by lazy { CardRepository(database.cardDao()) }
    val transactionRepository by lazy { TransactionRepository(database.transactionDao()) }
}