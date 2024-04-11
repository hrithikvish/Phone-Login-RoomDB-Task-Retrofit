package com.hrithikvish.apitask2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hrithikvish.apitask2.model.Data

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Data)

    @Query("Select * FROM customer_data_table WHERE customerid = :customerid")
    fun getData(customerid: Int): Data
}