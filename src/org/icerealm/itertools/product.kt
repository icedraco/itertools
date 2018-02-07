package org.icerealm.itertools

import kotlin.coroutines.experimental.buildSequence

fun product(vararg lists: Iterable<Any?>): Sequence<List<Any?>> = buildSequence {
    if (lists.isEmpty())
        return@buildSequence  // no combinations

    val iterators = Array(lists.size, { i -> lists[i].iterator() })
    if (iterators.any { !it.hasNext() })
        return@buildSequence  // no combinations available if one of the iterables is empty

    val accumulator = Array<Any?>(lists.size, { null })
    var index = 0
    while (index >= 0) {
        if (index >= lists.size) {
            yield(accumulator.toList())  // <-- return a combination
            index--
        } else {
            if (iterators[index].hasNext()) {
                accumulator[index] = iterators[index].next()
                index++
            } else {
                iterators[index] = lists[index].iterator()
                index--
            }
        }
    }
}
