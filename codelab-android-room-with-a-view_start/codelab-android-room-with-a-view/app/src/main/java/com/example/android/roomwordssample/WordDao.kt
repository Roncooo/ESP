package com.example.android.roomwordssample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM WORD_TABLE ORDER BY WORD ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)
    @Query("DELETE FROM WORD_TABLE")
    suspend fun deleteAll()
}