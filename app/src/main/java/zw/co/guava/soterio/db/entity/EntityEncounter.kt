package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encounters")
data class EntityEncounter(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = 0,
                           @ColumnInfo(name="Identifier") var Identifier: String,
                           @ColumnInfo(name="timestamp") var timestamp: Long,
                           @ColumnInfo(name="rssi") var rssi: Int,
                           @ColumnInfo(name="txPower") var txPower: Int)