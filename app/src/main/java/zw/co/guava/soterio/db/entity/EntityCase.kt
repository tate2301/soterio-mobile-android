package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cases")
data class EntityCase (
    @PrimaryKey @ColumnInfo(name = "Identifier") val identifier: String,
    @ColumnInfo(name = "tll") val tll: Long,
    @ColumnInfo(name="tul") val tul: Long,
    @ColumnInfo(name = "_id") val id: String)