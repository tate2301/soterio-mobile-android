package zw.ac.cut.soterio.sble.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keys")
data class EKey(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "keyIndex") val index: Int = 0)
