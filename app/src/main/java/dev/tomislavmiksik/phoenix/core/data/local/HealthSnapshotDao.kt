package dev.tomislavmiksik.phoenix.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.tomislavmiksik.phoenix.core.domain.model.HealthSnapshot
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface HealthSnapshotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(healthSnapshot: HealthSnapshot): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(healthSnapshots: List<HealthSnapshot>)

    @Update
    suspend fun update(healthSnapshot: HealthSnapshot)

    @Delete
    suspend fun delete(healthSnapshot: HealthSnapshot)

    @Query("SELECT * FROM HealthSnapshot ORDER BY id DESC")
    fun getAllSnapshots(): Flow<List<HealthSnapshot>>

    @Query("SELECT * FROM HealthSnapshot ORDER BY id DESC LIMIT 1")
    suspend fun getLatestSnapshot(): HealthSnapshot?

    @Query("SELECT * FROM HealthSnapshot ORDER BY id DESC LIMIT 1")
    fun getLatestSnapshotFlow(): Flow<HealthSnapshot?>

    @Query("SELECT * FROM HealthSnapshot WHERE id = :id")
    suspend fun getSnapshotById(id: Long): HealthSnapshot?

    @Query("SELECT * FROM HealthSnapshot WHERE sleepDuration >= :startDate AND sleepDuration <= :endDate ORDER BY sleepDuration DESC")
    suspend fun getSnapshotsInDateRange(startDate: LocalDateTime, endDate: LocalDateTime): List<HealthSnapshot>

    @Query("SELECT * FROM HealthSnapshot WHERE sleepDuration >= :startDate AND sleepDuration <= :endDate ORDER BY sleepDuration DESC")
    fun getSnapshotsInDateRangeFlow(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<HealthSnapshot>>

    @Query("DELETE FROM HealthSnapshot WHERE id NOT IN (SELECT id FROM HealthSnapshot ORDER BY id DESC LIMIT :keepCount)")
    suspend fun deleteOldSnapshots(keepCount: Int)

    @Query("DELETE FROM HealthSnapshot")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM HealthSnapshot")
    suspend fun getCount(): Int
}
