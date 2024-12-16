package com.example.mycomposenotes.di

import org.koin.dsl.module

val dataBaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}