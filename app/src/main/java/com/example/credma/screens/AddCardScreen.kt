package com.example.credma.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.credma.components.ATMCard
import com.example.credma.constants.BankName
import com.example.credma.constants.CardIssuer
import com.example.credma.constants.CardType
import com.example.credma.model.CreditCard
import com.example.credma.viewmodel.CardViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    viewmodel: CardViewModel,
    navController: NavController
) {

    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var selectedBank by remember { mutableStateOf<BankName>(BankName.HDFC) }
    var expandedSelectedBank by remember { mutableStateOf(false) }
    var selectedCardType by remember { mutableStateOf<CardType>(CardType.DEBIT) }
    var expandedCardType by remember { mutableStateOf(false) }
    var selectedCardIssuer by remember { mutableStateOf<CardIssuer>(CardIssuer.VISA) }
    var expandedCardIssuer by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val dateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
                            expiryDate = dateFormat.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Card") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            ATMCard(
                cardHolderName = name.ifEmpty { "Your Name" },
                cardNumber = number.ifEmpty { "1234 5678 9012 3456" },
                cardExpiryDate = expiryDate.ifEmpty { "MM/YY" },
                cardCvv = cvv.ifEmpty { "XXX" },
                cardType = selectedCardType.name,
                bankName = selectedBank,
                bankCardType = "Platinum",
                cardIssuer = selectedCardIssuer,
                isMasked = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = number,
                onValueChange = { if(it.length <= 16) number = it },
                label = { Text("Card Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = { Text("Expiry Date") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select Date",
                            modifier = Modifier.clickable { showDatePicker = true }
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    label = { Text("CVV") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedBank.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Bank Name") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Bank",
                            modifier = Modifier.clickable { expandedSelectedBank = true }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expandedSelectedBank,
                    onDismissRequest = { expandedSelectedBank = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val banks = listOf(
                        BankName.HDFC, BankName.SBI, BankName.ICICI, BankName.AXIS,
                        BankName.KOTAK, BankName.YES, BankName.BOB, BankName.PNB
                    )
                    banks.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(text = bank.name) },
                            onClick = {
                                selectedBank = bank
                                expandedSelectedBank = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    value = selectedCardType.name,
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Card Type",
                            modifier = Modifier.clickable { expandedCardType = true }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expandedCardType,
                    onDismissRequest = { expandedCardType = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val cardTypes = listOf(CardType.CREDIT, CardType.DEBIT)
                    cardTypes.forEach { cardType ->
                        DropdownMenuItem(
                            text = { Text(text = cardType.name) },
                            onClick = {
                                selectedCardType = cardType
                                expandedCardType = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    value = selectedCardIssuer.name,
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Card Type",
                            modifier = Modifier.clickable { expandedCardIssuer = true }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expandedCardIssuer,
                    onDismissRequest = { expandedCardIssuer = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val cardIssuers = listOf(CardIssuer.VISA, CardIssuer.MASTERCARD, CardIssuer.RUPAY)
                    cardIssuers.forEach { cardIssuer ->
                        DropdownMenuItem(
                            text = { Text(text = cardIssuer.name) },
                            onClick = {
                                selectedCardIssuer = cardIssuer
                                expandedCardIssuer = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    viewmodel.addCard(
                        CreditCard(
                            cardHolderName = name,
                            cardNumber = number,
                            cardExpiryDate = expiryDate,
                            cardCvv = cvv,
                            cardType = CardType.DEBIT,
                            bankName = selectedBank,
                            bankCardType = "Platinum",
                            cardIssuer = CardIssuer.VISA
                        )
                    )
                    navController.popBackStack()
                }
            ) {
                Text("Add Card")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}