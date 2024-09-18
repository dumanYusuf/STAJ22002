package com.example.hiltkullanimi

import javax.inject.Inject
import javax.inject.Singleton


// constructer injection
@Singleton// bu yapı en üstte oldugu için sıkındı cıkarmaz biz Mussiseni activitMani de kullandıgımız için onun üstündeki scope kavramlarını verebiliriz
class Mussien
     @Inject
     constructor(instrument:Instrument,band: Band) {

    fun sing(){
        println("single")
    }
}