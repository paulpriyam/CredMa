package com.example.credma.repository

import com.example.credma.data.dao.CardDao
import com.example.credma.model.CreditCard

class CardRepository(private val cardDao: CardDao) {
    fun getAllCards() = cardDao.getAllCards()

    suspend fun insertCard(card: CreditCard) {
        cardDao.insertCard(card)
    }

    suspend fun deleteCard(card: CreditCard) {
        cardDao.deleteCard(card)
    }
}