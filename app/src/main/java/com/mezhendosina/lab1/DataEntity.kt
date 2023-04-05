package com.mezhendosina.lab1

object CardType {
    const val BIG_CARD = 0
    const val SMALL_CARD = 1
}

data class DataEntity(val id: Int, val type: Int)


fun generateList(size: Int = 10): MutableList<DataEntity> {
    val outList = mutableListOf<DataEntity>()
    for (i in 1..size) {
        outList.add(
            DataEntity(
                i,
                i % 2
            )
        )
    }
    return outList
}