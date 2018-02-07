package org.icerealm.itertools

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PermutationsTest {

    @Test
    fun `empty list returns nothing`() {
        assertEquals(0, permutations(listOf(), 1).count())
    }

    @Test
    fun `qty of zero returns nothing`() {
        assertEquals(0, permutations(listOf(1, 2, 3), 0).count())
    }

    @Test
    fun `qty bigger than list size returns nothing`() {
        assertEquals(0, permutations(listOf(1, 2, 3), 4).count())
    }

    @Test
    fun `qty of one returns each value`() {
        assertEquals(3, permutations(listOf(1, 2, 3), 1).count())

        val permutations = permutations(listOf(1, 2, 3), 1).toList()
        assertTrue(listOf(1) in permutations)
        assertTrue(listOf(2) in permutations)
        assertTrue(listOf(3) in permutations)
    }

    @Test
    fun `all 2-permutations present in list of three`() {
        val permutations = permutations(listOf(1, 2, 3), 2).toList()
        assertTrue(listOf(1, 2) in permutations)
        assertTrue(listOf(1, 3) in permutations)
        assertTrue(listOf(2, 1) in permutations)
        assertTrue(listOf(2, 3) in permutations)
        assertTrue(listOf(3, 1) in permutations)
        assertTrue(listOf(3, 2) in permutations)
        assertEquals(6, permutations.size)
    }

    @Test
    fun `all 3-permutations present in list of three`() {
        val permutations = permutations(listOf(1, 2, 3), 3).toList()
        assertTrue(listOf(1, 2, 3) in permutations)
        assertTrue(listOf(3, 1, 2) in permutations)
        assertTrue(listOf(2, 3, 1) in permutations)

        assertTrue(listOf(2, 1, 3) in permutations)
        assertTrue(listOf(3, 2, 1) in permutations)
        assertTrue(listOf(1, 3, 2) in permutations)

        assertEquals(6, permutations.size)
    }

    @Test
    fun `qty equal to list size returns similar list`() {
        assertEquals(listOf(1, 2, 3), permutations(listOf(1, 2, 3), 3).first())
    }
}