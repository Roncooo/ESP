package com.example.android.roomwordssample

import android.app.Application

class WordsApplication : Application() {
    // anche per le val si può specificare una inizializzazione lazy
    // (come nel design pattern proxy) l'oggetto viene istanziato solo quando è veramente necessario
    // in questo caso particolare sarà istanziato subito lo stesso perché deve far partire il recycler view
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}