package dev.tomislavmiksik.phoenix.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Entity representing a clipboard entry stored in the local database.
 */
@Entity(tableName = "clipboard_entries")
data class ClipboardEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,
    val type: ClipboardType = ClipboardType.TEXT,
    val timestamp: LocalDateTime,
    val isPinned: Boolean = false
)

enum class ClipboardType {
    TEXT
    // TODO: Add IMAGE, GIF, URL support when implementing media clipboard
}
