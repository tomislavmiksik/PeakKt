package dev.tomislavmiksik.phoenix.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.tomislavmiksik.phoenix.core.data.local.converters.LocalDateTimeConverter
import dev.tomislavmiksik.phoenix.core.data.local.dao.ClipboardDao
import dev.tomislavmiksik.phoenix.core.data.local.entity.ClipboardEntry

@Database(
    entities = [
        ClipboardEntry::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LocalDateTimeConverter::class,
)
abstract class PhoenixDatabase : RoomDatabase() {
    abstract fun clipboardDao(): ClipboardDao

    companion object {
        private const val DB_NAME = "phoenix_database"

        @Volatile
        @Suppress("ktlint:standard:property-naming")
        private var INSTANCE: PhoenixDatabase? = null

        fun getInstance(context: Context): PhoenixDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PhoenixDatabase::class.java,
                        DB_NAME,
                    ).fallbackToDestructiveMigration()
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
