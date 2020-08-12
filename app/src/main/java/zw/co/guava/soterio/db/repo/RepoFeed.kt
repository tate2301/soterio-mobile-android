package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.entity.EntityFeed
import zw.co.guava.soterio.db.dao.DaoFeed

class RepoFeeds(private val daoFeed: DaoFeed) {
    fun saveFeed(Feed: EntityFeed) {
        daoFeed.saveFeed(Feed)
    }

    fun saveAllFeeds(Feeds: List<EntityFeed>) {
        daoFeed.saveAllFeeds(Feeds)
    }
}