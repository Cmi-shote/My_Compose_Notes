package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.data.database.provideDao
import com.example.mycomposenotes.notes.data.database.provideDataBase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}