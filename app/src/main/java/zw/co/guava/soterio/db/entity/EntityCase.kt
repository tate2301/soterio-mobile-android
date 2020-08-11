package zw.co.soterio.monitor.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cases")
data class Case (
    @PrimaryKey @ColumnInfo(name = "Identifier") val Identifier: String,
    @ColumnInfo(name = "tll") val tll: Long,
    @ColumnInfo(name="tul") val tul: Long)