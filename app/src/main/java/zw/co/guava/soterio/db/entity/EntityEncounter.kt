package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encounters")
data class EntityEncounter(@PrimaryKey @ColumnInfo val Identifier: String, @ColumnInfo val timestamp: Long)