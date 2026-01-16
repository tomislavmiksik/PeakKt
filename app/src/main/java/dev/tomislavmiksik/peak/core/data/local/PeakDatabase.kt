package dev.tomislavmiksik.peak.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.tomislavmiksik.peak.core.data.local.converters.LocalDateTimeConverter
import dev.tomislavmiksik.peak.core.domain.model.HealthSnapshot

@Database(
    entities = [HealthSnapshot::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    LocalDateTimeConverter::class,
)
abstract class PeakDatabase : RoomDatabase() {
    abstract fun healthSnapshotDao(): HealthSnapshotDao
}
