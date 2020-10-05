package zw.co.guava.soterio.sync

import com.github.nkzawa.emitter.Emitter
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import zw.ac.cut.soterio.sble.features.CentralLog
import zw.co.guava.soterio.SoterioApplication
import zw.co.guava.soterio.db.entity.EntityCase
import zw.co.guava.soterio.db.entity.EntityEncounter
import zw.co.guava.soterio.db.entity.EntityFeed
import zw.co.guava.soterio.db.repo.RepoCases
import zw.co.guava.soterio.db.repo.RepoFeeds


class ChangeStreamSync(var webSocket: com.github.nkzawa.socketio.client.Socket?) {
    private val TAG = "ChangeStreamSync"

    fun listenForIdentifiers() {
        webSocket!!.on("new_case", object: Emitter.Listener {
            override fun call(vararg args: Any?) {
                val data = args[0] as JSONArray

                var identifier: String
                var tul: String
                var tll: String
                var id: String

                val thread = object: Thread() {
                    override fun run() {
                        super.run()
                        val daoCases = SoterioApplication.database!!.daoCases()

                        for (i in 0 until data.length()) {
                            val caseToken = data.getJSONObject(i)
                            try {
                                identifier = caseToken.getString("identifier")
                                tul = caseToken.getString("tul")
                                tll = caseToken.getString("tll")
                                id = caseToken.getString("_id")

                                val entityCase = EntityCase(id = id, tll = tll.toLong(), tul = tul.toLong(), identifier = identifier)
                                RepoCases(daoCases).saveCase(entityCase)
                                CentralLog.d(TAG, "Event:Received Case Token $identifier, $tul, $tll, $id")

                            } catch (e: JSONException) {
                                return
                            }
                        }
                    }
                }

                thread.start()

            }

        })
    }

    fun listenForFeed() {
        webSocket!!.on("feed", object: Emitter.Listener {
            override fun call(vararg args: Any?) {
                val data = args[0] as JSONArray

                var title: String
                var timestamp: String
                var text: String

                val thread = object: Thread() {
                    override fun run() {
                        super.run()

                        val daoFeed = SoterioApplication.database!!.daoFeed()


                        for (i in 0 until data.length()) {
                            val feedPacket = data.getJSONObject(i)
                            try {
                                title = feedPacket.getString("title")
                                timestamp = feedPacket.getString("timestamp")
                                text = feedPacket.getString("text")

                                val entityFeed = EntityFeed(title = title, text = text, timestamp = timestamp.toLong())

                                RepoFeeds(daoFeed).saveFeed(entityFeed)
                                CentralLog.d(TAG, "Event:Received Feed: $title, $timestamp")

                            } catch (e: JSONException) {
                                return
                            }
                        }

                    }
                }

                thread.start()

            }

        })
    }

    // To be used only during CUT debug test
    fun emmitEncounter(encounter: EntityEncounter) {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid

        webSocket!!.emit("encounter", "{ uid: ${uid}}, encounter: ${Gson().toJson(encounter)}")
    }
}