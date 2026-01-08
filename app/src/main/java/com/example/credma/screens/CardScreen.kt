package com.example.credma.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.credma.R
import com.example.credma.components.ATMCard
import com.example.credma.components.CardOptions
import com.example.credma.components.TransactionItem
import com.example.credma.viewmodel.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(
    navController: NavController,
    viewmodel: CardViewModel
) {
    val cards = viewmodel.cards.collectAsState()
    val transactions = viewmodel.transactions.collectAsState()
    val pagerState = rememberPagerState(pageCount = { cards.value.size })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cards") },
                actions = {
                    IconButton(onClick = { navController.navigate("add_card") }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        )
        {
            Spacer(modifier = Modifier.height(16.dp))

            if (cards.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Cards Added")
                }
            } else {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(16.dp),
                    pageSpacing = 16.dp

                    ) { page ->
                    ATMCard(
                        cardHolderName = cards.value[page].cardHolderName,
                        cardNumber = cards.value[page].cardNumber,
                        cardExpiryDate = cards.value[page].cardExpiryDate,
                        cardCvv = cards.value[page].cardCvv,
                        cardType = cards.value[page].cardType.name,
                        bankName = cards.value[page].bankName,
                        cardIssuer = cards.value[page].cardIssuer,
                        bankCardType = cards.value[page].bankCardType,
                        isMasked = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CardOptions(
                    modifier = Modifier.weight(1f),
                    drawableResourceId = R.drawable.ic_card_withdrawal,
                    text = "View Card Details",
                    onClick = {}
                )
                CardOptions(
                    modifier = Modifier.weight(1f),
                    drawableResourceId = R.drawable.ic_card_info,
                    text = "View Card Details",
                    onClick = {}
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CardOptions(
                    modifier = Modifier.weight(1f),
                    drawableResourceId = R.drawable.ic_card_withdrawal,
                    text = "View Card Details",
                    onClick = {}
                )
                CardOptions(
                    modifier = Modifier.weight(1f),
                    drawableResourceId = R.drawable.ic_card_info,
                    text = "View Card Details",
                    onClick = {}
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Transactions",
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text("See All",fontSize = 18.sp,
                    color = Color.Blue
                )
            }
            if(transactions.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Transactions")
                }
            }else{
                LazyColumn() {
                    items(transactions.value.size) { transaction ->
                        TransactionItem(transactions.value[transaction])
                    }
                }
            }




        }
    }
}