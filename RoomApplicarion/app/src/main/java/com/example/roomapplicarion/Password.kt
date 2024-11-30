package com.example.roomapplicarion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "passwords")
data class Password(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var AccountName: String,
    var Email: String,
    var Password: String

)


@Dao
interface PasswordDAO {

    @Upsert
    suspend fun upsertPassword(password: Password)

    @Delete
    suspend fun  deletePassword(password: Password)

    @Query("SELECT * FROM passwords ORDER BY AccountName")
    fun getPasswordByName(): Flow<List<Password>>

}


