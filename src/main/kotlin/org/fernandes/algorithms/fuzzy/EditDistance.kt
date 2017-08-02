package org.fernandes.algorithms

/**
 * Edit distance implementation in two variations: recursive or dynamic programming.
 */
class EditDistance {

    fun count(s1: CharSequence, s2: CharSequence): Int {
        return count(s1, s2, 0, 0)
    }

    private fun count(s1: CharSequence, s2: CharSequence, m: Int, n: Int): Int {
        return count(s1, s2, m, n, HashMap())
    }

    private fun count(s1: CharSequence, s2: CharSequence, m: Int, n: Int, cache: MutableMap<Pair<Int, Int>, Int>): Int {
        if (n == s2.length) {
            return s1.length - m
        }
        if (m == s1.length) {
            return s2.length - n
        }
        if (s1[m] == s2[n]) {
            return countCached(cache, m + 1, n + 1, s1, s2)
        } else {
            return 1 + Math.min(
                    Math.min(countCached(cache, m, n + 1, s1, s2), countCached(cache, m + 1, n, s1, s2)),
                    countCached(cache, m + 1, n + 1, s1, s2))
        }
    }

    private fun countCached(cache: MutableMap<Pair<Int, Int>, Int>, m1: Int, n1: Int, s1: CharSequence, s2: CharSequence): Int {
        val dist = cache[Pair(m1, n1)]
        if (dist != null) {
            return dist
        } else {
            val c = count(s1, s2, m1, n1, cache)
            cache.put(Pair(m1, n1), c)
            return c
        }
    }

    fun countDynamic(s1: CharSequence, s2: CharSequence): Int {
        return countDynamicArray(s1, s2).first
    }

    fun countDynamicArray(s1: CharSequence, s2: CharSequence): Pair<Int, Array<IntArray>> {
        val m = s1.length
        val n = s2.length
        val v = Array(m + 1, { IntArray(n + 1) })
        for (i in 0..m) v[i][0] = i
        for (i in 0..n) v[0][i] = i

        for (i in 1..m) {
            for (j in 1..n) {
                if (s1[i - 1] == s2[j - 1]) {
                    v[i][j] = v[i - 1][j - 1]
                } else {
                    v[i][j] = 1 + Math.min(Math.min(v[i][j - 1], v[i - 1][j]), v[i - 1][j - 1])
                }
//                printOut(v)
            }
        }
        return Pair(v[m][n], v)
    }

}