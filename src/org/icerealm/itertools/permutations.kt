package org.icerealm.itertools

import kotlin.coroutines.experimental.buildSequence

fun permutations(items: List<Any?>, qty: Int): Sequence<List<Any?>> = buildSequence {
    if (items.size < qty || qty == 0)
        return@buildSequence

    val availableIndices = Array<Int?>(items.size, { it })
    val iterators = Array<Iterator<Int>>(qty, { (0 until availableIndices.size).iterator() })
    val accumulator = Array<Int?>(qty, { null })
    var index = 0
    while (index >= 0) {
        if (index >= qty) {
            yield(accumulator.map { i -> items[i!!] }.toList())  // <-- return a permutation
            index--
        } else {
            if (iterators[index].hasNext()) {
                // return the value we took (if any)
                if (accumulator[index] != null) {
                    availableIndices[accumulator[index]!!] = accumulator[index]
                    accumulator[index] = null
                }

                // take the value from available indices and store it
                val selectedIndex = iterators[index].next()
                accumulator[index] = availableIndices[selectedIndex]
                availableIndices[selectedIndex] = null

                if (accumulator[index] != null) {
                    index++
                }
            } else {
                // return the value we took
                if (accumulator[index] != null) {
                    availableIndices[accumulator[index]!!] = accumulator[index]
                    accumulator[index] = null
                }

                // reset iterator
                iterators[index] = (0 until availableIndices.size).iterator()
                index--
            }
        }
    }
}
