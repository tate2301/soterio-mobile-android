package zw.co.guava.soterio.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testingcentres")
data class EntityTestingCentre(
    @PrimaryKey @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name="phone") val phone: String
)