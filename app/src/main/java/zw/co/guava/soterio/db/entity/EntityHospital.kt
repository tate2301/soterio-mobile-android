package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospitals")
data class EntityHospital(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0)