package org.icerealm.itertools

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductTest {

    @Test
    fun `empty list returns nothing`() {
        assertEquals(0, product().count())
    }

    @Test
    fun `proper single list elements count`() {
        assertEquals(3, product(listOf(3, 2, 1)).count())
    }

    @Test
    fun `all single list elements present`() {
        val results = product(listOf(3, 2, 1)).toList()
        assertEquals(3, results[0][0])
        assertEquals(2, results[1][0])
        assertEquals(1, results[2][0])
    }

    @Test
    fun `proper dual list elements count`() {
        assertEquals(9, product(listOf(3, 2, 1), listOf(7, 8, 9)).count())
    }

    @Test
    fun `all dual list elements present`() {
        val results = product(listOf(3, 2, 1), listOf(7, 8, 9)).map { combo -> combo[0] to combo[1] }.toList()
        for (i in 3 downTo 1) {
            for (j in 7..9) {
                assertTrue(Pair(i,j) in results, "($i,$j)")
            }
        }
    }

    @Test
    fun `no combinations if one iterable is empty`() {
        assertEquals(0, product(
                listOf(3, 2, 1),
                listOf(),
                listOf(7, 8, 9)
        ).count())
    }
}