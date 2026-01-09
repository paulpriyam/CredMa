package com.example.credma.di

import android.content.Context
import androidx.room.Room
import com.example.credma.data.CardDatabase
import com.example.credma.data.dao.CardDao
import com.example.credma.data.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCardDatabase(@ApplicationContext context: Context): CardDatabase {
        return Room.databaseBuilder(
            context,
            CardDatabase::class.java,
            CardDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCardDao(database: CardDatabase): CardDao {
        return database.cardDao()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: CardDatabase): TransactionDao {
        return database.transactionDao()
    }
}
