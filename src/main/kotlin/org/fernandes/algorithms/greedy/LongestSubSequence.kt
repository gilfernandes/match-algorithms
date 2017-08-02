package org.fernandes.algorithms.greedy

/**
 * Longest contiguous subsequence algorithm.
 */
class LongestContiguousSubSequence {

    fun findLength(array: IntArray): Int {
        val set: MutableSet<Int> = HashSet()
        array.forEach { set.add(it) }

        var max: Int = 0
        for(i in 0..array.size - 1) {
            if(!set.contains(array[i] - 1)) { // look for array start
                var j = 0
                while(set.contains(array[i] + j)){
                    j += 1
                }
                max = Math.max(j, max)
            }
        }
        return max
    }
}