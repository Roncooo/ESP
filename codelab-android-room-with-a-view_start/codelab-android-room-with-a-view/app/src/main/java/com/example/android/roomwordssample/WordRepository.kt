package com.example.android.roomwordssample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// This is the interface between db and external word
class WordRepository(private val wordDao: WordDao){

    // Room executes all queries on a separate thread
    // Observer Flow will notify the observer when data has changed
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // The annotation lets the function to be executed in a separated thread
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

}