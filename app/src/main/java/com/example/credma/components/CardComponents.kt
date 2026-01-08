package com.example.credma.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.credma.R
import com.example.credma.constants.BankName
import com.example.credma.constants.CardIssuer
import com.example.credma.model.Transaction
import com.example.credma.ui.theme.CredMaTheme

@Composable
fun ATMCard(
    cardHolderName:String,
    cardNumber:String,
    cardExpiryDate:String,
    cardCvv:String,
    cardType:String,
    bankName: BankName,
    bankCardType:String,
    cardIssuer: CardIssuer,
    isMasked: Boolean
) {
    Card(shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)

        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = cardHolderName,
                    fontSize = 24.sp
                )
                Image(painter = painterResource(id = bankName.drawableResourceId),
                    contentDescription = bankName.name,
                    modifier = Modifier.width(100.dp).height(60.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = bankCardType,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (isMasked) {
                Text(text = "${cardNumber.take(4)} **** **** ${cardNumber.takeLast(4)}",
                    fontSize = 28.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(text = cardNumber,
                    fontSize = 28.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = "Expiry Date",
                        fontSize = 16.sp
                    )
                    Text(text = cardExpiryDate,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "CVV",
                        fontSize = 16.sp
                    )
                    Text(text = cardCvv,
                        fontSize = 20.sp
                    )
                }
                Image(painter = painterResource(id = cardIssuer.drawableResourceId),
                    contentDescription = cardIssuer.name,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }

}

@Composable
fun CardOptions(
    modifier: Modifier = Modifier,
    drawableResourceId: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = onClick)
        ) {
            Image(painter = painterResource(id = drawableResourceId),
                contentDescription = "Eye",
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = transaction.description,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(text = "₹${transaction.amount}",
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ATMCardPreviewMasked() {
    CredMaTheme {
        ATMCard(
            cardHolderName = "John Doe",
            cardNumber = "1234 5678 9012 3456",
            cardExpiryDate = "12/26",
            cardCvv = "123",
            cardType = "Debit",
            bankName = BankName.HDFC,
            bankCardType = "Platinum",
            cardIssuer = CardIssuer.VISA,
            isMasked = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ATMCardPreview() {
    CredMaTheme {
        ATMCard(
            cardHolderName = "John Doe",
            cardNumber = "1234 5678 9012 3456",
            cardExpiryDate = "12/26",
            cardCvv = "123",
            cardType = "Debit",
            bankName = BankName.HDFC,
            bankCardType = "Platinum",
            cardIssuer = CardIssuer.VISA,
            isMasked = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardOptionsPreview() {
    CredMaTheme {
        CardOptions(
            drawableResourceId = R.drawable.ic_card_withdrawal,
            text = "View Card Details",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    CredMaTheme {
        TransactionItem(
            transaction = Transaction(
                cardId = "1",
                amount = 1200.0,
                date = "2023-10-10",
                description = "Amazon Purchase",
                category = "Shopping"
            )
        )
    }
}
