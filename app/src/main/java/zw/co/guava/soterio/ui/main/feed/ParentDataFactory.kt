package zw.co.guava.soterio.ui.main.feed

import java.util.*

object ParentDataFactory{
    private val random = Random()

    private val titles =  arrayListOf("Thu, Jul 22 - 14:22", "Thu, Jul 22 - 16:34", "Fri, Jul 23 - 10:10", "Fri, Jul 23 - 11:12", "Fri, Jul 23 - 11:54")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren() : List<ChildModel>{
        return ChildDataFactory.getChildren(4)
    }

    fun getParents(count : Int) : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        repeat(count){
            val parent = ParentModel(randomTitle(), randomChildren())
            parents.add(parent)
        }
        return parents
    }
}