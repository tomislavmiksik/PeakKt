package dev.tomislavmiksik.phoenix.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.tomislavmiksik.phoenix.core.data.local.entity.ClipboardEntry
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for clipboard entries.
 */
@Dao
interface ClipboardDao {

    /**
     * Get all clipboard entries ordered by pinned status and timestamp.
     * Pinned entries appear first, then sorted by most recent.
     */
    @Query("SELECT * FROM clipboard_entries ORDER BY isPinned DESC, timestamp DESC")
    fun getAllEntries(): Flow<List<ClipboardEntry>>

    /**
     * Search clipboard entries by content.
     * TODO: Implement full-text search for better performance on large datasets
     */
    @Query("SELECT * FROM clipboard_entries WHERE content LIKE '%' || :query || '%' ORDER BY isPinned DESC, timestamp DESC")
    fun searchEntries(query: String): Flow<List<ClipboardEntry>>

    @Query("SELECT * FROM clipboard_entries WHERE id = :id")
    suspend fun getEntryById(id: Long): ClipboardEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: ClipboardEntry): Long

    @Update
    suspend fun updateEntry(entry: ClipboardEntry)

    @Delete
    suspend fun deleteEntry(entry: ClipboardEntry)

    @Query("DELETE FROM clipboard_entries")
    suspend fun deleteAllEntries()

    /**
     * Delete old entries beyond a certain limit, keeping pinned entries.
     * TODO: Call this from WorkManager cleanup job
     */
    @Query("DELETE FROM clipboard_entries WHERE isPinned = 0 AND id NOT IN (SELECT id FROM clipboard_entries WHERE isPinned = 0 ORDER BY timestamp DESC LIMIT :limit)")
    suspend fun deleteOldEntries(limit: Int)
}
