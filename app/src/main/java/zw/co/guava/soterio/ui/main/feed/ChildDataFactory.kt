package zw.co.guava.soterio.ui.main.feed

import java.util.*

object ChildDataFactory {

    private val random = Random()

    private val titles =
        arrayListOf("Thu, Jul 22 - 14:22", "Thu, Jul 22 - 16:34", "Fri, Jul 23 - 10:10", "Fri, Jul 23 - 11:12", "Fri, Jul 23 - 11:54")

    private fun randomTitle(): String {
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    fun getChildren(count: Int): List<ChildModel> {
        val children = mutableListOf<ChildModel>()
        repeat(count) {
            val child = ChildModel(randomTitle())
            children.add(child)
        }
        return children
    }
}