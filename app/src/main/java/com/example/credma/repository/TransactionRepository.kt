package com.example.credma.repository

import com.example.credma.data.dao.TransactionDao
import com.example.credma.model.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao) {

    fun getAllTransactions() = transactionDao.getAllTransactions()
    fun getTransactionsByCardId(cardId: String) = transactionDao.getTransactionsByCardId(cardId)
    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }
}