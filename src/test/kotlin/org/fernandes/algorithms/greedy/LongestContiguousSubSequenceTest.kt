package org.fernandes.algorithms.greedy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class LongestContiguousSubSequenceTest {

    val lcs: LongestContiguousSubSequence = LongestContiguousSubSequence()

    @Test
    @DisplayName("When find longest subsequence should find longest")
    fun findLength() {
        testLCS(intArrayOf(1, 9, 3, 10, 4, 20, 2), 4)
        testLCS(intArrayOf(1, 9, 3, 10, 4, 20, 2, 21, 22, 23, 24), 5)
    }

    private fun testLCS(array: IntArray, expected: Int) {
        val res = lcs.findLength(array)
        assertThat(res).isEqualTo(expected)
        println(res)
    }

}