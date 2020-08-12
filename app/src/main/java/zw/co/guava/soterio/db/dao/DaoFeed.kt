package zw.co.guava.soterio.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityFeed

@Dao
interface DaoFeed{

    @Query ("SELECT * FROM feed")
    fun getAllFeeds():LiveData<List<EntityFeed>>


    @Insert
    fun saveFeed(Feed: EntityFeed)

    @Insert()
    fun saveAllFeeds(Feeds: List<EntityFeed>)

}