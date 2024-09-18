package com.example.hiltkullanimi

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

interface MyInterface {
    fun myPrintFunction():String
}


@InstallIn(ApplicationComponentManager::class)// burda yine scope kullanabilirşz tabi burda application kullandıgım içim allta buna singleton demem lazım
@Module
class myModule{
    @Singleton
    @Provides
    fun providerFunction():MyInterface{
        return InterfaceImplementer()
    }
}



