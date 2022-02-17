package com.example.mula.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mula.data.model.Allocation

@Dao
interface AllocationDao {
    @Query("SELECT * FROM allocation WHERE balanceId = :id")
    fun getAllocations(id: Int): LiveData<List<Allocation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllocation(newAllocation: Allocation)

    @Delete(entity = Allocation::class)
    fun deleteAllocation(allocation: Allocation)
}