package com.harr1424.listmaker.data

import androidx.room.*
import com.harr1424.listmaker.model.DetailItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailItemDao {
    @Query("SELECT * FROM DetailItem ORDER BY detail_item_name ASC")
    fun getAll(): Flow<List<DetailItem>>

    @Query("SELECT * FROM DetailItem WHERE detail_item_name LIKE :name")
    fun findByName(name: String): Flow<List<DetailItem>>

    @Query("SELECT * FROM DetailItem " +
            "INNER JOIN MainItem ON MainItem.id = DetailItem.main_item_id " +
            "WHERE DetailItem.main_item_id = :mainItemId ")
    fun getDetailFromMain(mainItemId: Int) : Flow<List<DetailItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: DetailItem)

    @Delete
    fun delete(item: DetailItem)

    @Query("UPDATE DetailItem SET detail_item_name = :name WHERE " +
            "main_item_id = :mainItemId AND detail_item_name = :detailItemName ")
    fun update(mainItemId: Int, detailItemName: String, name: String)
}