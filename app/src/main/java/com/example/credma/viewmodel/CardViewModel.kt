package com.example.credma.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.credma.constants.BankName
import com.example.credma.constants.CardIssuer
import com.example.credma.constants.CardType
import com.example.credma.model.CreditCard
import com.example.credma.model.Transaction
import com.example.credma.repository.CardRepository
import com.example.credma.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
): ViewModel() {

    private val _cards = MutableStateFlow<List<CreditCard>>(listOf(
        CreditCard(
            cardHolderName = "John Doe",
            cardNumber = "1234567890123456",
            cardExpiryDate = "12/26",
            cardCvv = "123",
            bankName = BankName.HDFC,
            cardIssuer = CardIssuer.VISA,
            bankCardType = "Platinum",
            cardType = CardType.DEBIT
        ),
        CreditCard(
            cardHolderName = "Jane Doe",
            cardNumber = "1234567890123456",
            cardExpiryDate = "12/26",
            cardCvv = "123",
            bankName = BankName.SBI,
            cardIssuer = CardIssuer.MASTERCARD,
            bankCardType = "Gold",
            cardType = CardType.CREDIT
        ),
        CreditCard(
            cardHolderName = "John Doe",
            cardNumber = "1234567890123456",
            cardExpiryDate = "12/26",
            cardCvv = "123",
            bankName = BankName.ICICI,
            cardIssuer = CardIssuer.RUPAY,
            bankCardType = "Platinum",
            cardType = CardType.DEBIT
        )
    ))
    val cards: StateFlow<List<CreditCard>> = cardRepository.getAllCards().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000), emptyList())

    fun addCard(card: CreditCard) {
        viewModelScope.launch {
            cardRepository.insertCard(card)
        }
    }

    private val _transactions = MutableStateFlow<List<Transaction>>(listOf(
        Transaction(
            cardId = "1",
            amount = 1200.0,
            date = "2023-10-10",
            description = "Amazon Purchase",
            category = "Shopping"
        ),
        Transaction(
            cardId = "2",
            amount = 1200.0,
            date = "2023-10-10",
            description = "Amazon Purchase",
            category = "Shopping"
        ),
        Transaction(
            cardId = "2",
            amount = 1200.0,
            date = "2023-10-10",
            description = "Amazon Purchase",
            category = "Shopping"
        )
    ))
    val transactions: StateFlow<List<Transaction>> = transactionRepository.getAllTransactions().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000), emptyList())
    fun getTransactionsByCardId(cardId: String) = transactionRepository.getTransactionsByCardId(cardId).stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.insertTransaction(transaction)
        }
    }
}