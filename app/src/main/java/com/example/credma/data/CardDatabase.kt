package com.example.credma.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.credma.data.dao.CardDao
import com.example.credma.data.dao.TransactionDao
import com.example.credma.model.CreditCard
import com.example.credma.model.Transaction

@Database(entities = [CreditCard::class, Transaction::class], version = 1)
@TypeConverters(Converters::class)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun transactionDao(): TransactionDao
    companion object {
        const val DATABASE_NAME = "card_database"

        @Volatile
        private var INSTANCE: CardDatabase? = null
        fun getDatabase(context: Context): CardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}