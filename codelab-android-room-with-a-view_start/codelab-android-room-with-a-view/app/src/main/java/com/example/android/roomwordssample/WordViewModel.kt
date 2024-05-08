package com.example.android.roomwordssample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    // viewModelScope è un attributo ereditato da ViewModel
    // e fornisce uno scope per le coroutines
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}


// Design pattern factory: risolve lo stesso problema del dependency injection
// WordViewModelFactory si occupa di creare gli oggetti di WordVIewModel avendo in mano le dipendenze

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


