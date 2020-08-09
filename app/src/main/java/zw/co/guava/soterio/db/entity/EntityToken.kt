package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tokens")
class EntityToken(
    @PrimaryKey @ColumnInfo(name = "Identifier") val Identifier: String,
    @ColumnInfo(name = "tll") val tll: Long,
    @ColumnInfo(name="tul") val tul: Long)