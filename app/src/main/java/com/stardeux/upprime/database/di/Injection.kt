package com.stardeux.upprime.database.di

import android.content.Context
import androidx.room.Room
import com.stardeux.upprime.database.UpPrimeDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideUpPrimeDatabase(get()) }
}

private fun provideUpPrimeDatabase(context: Context): UpPrimeDatabase {
    return Room.databaseBuilder(context, UpPrimeDatabase::class.java, UpPrimeDatabase.DB_NAME)
        .fallbackToDestructiveMigrationFrom(1)
        .build()
}