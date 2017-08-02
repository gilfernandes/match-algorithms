package org.fernandes.algorithms.fuzzy

/**
 * Optimal string alignment distance. See https://en.wikipedia.org/wiki/Damerau–Levenshtein_distance
 * for more information.
 */
class DamerauLevenshtein {

    fun osaDistanceCount(s1: CharSequence, s2: CharSequence): Int {
        return osaDistance(s1, s2).first
    }

    /**
     * Optimal string alignment distance or restricted edit distance. The distance of the transposition is
     * always one.
     */
    fun osaDistance(s1: CharSequence, s2: CharSequence): Pair<Int, Array<IntArray>> {
        val m = s1.length
        val n = s2.length
        val matrix = Array(m + 1, { IntArray(n + 1) })
        for (i in 0..m) {
            matrix[i][0] = i
        }
        for (i in 0..n) {
            matrix[0][i] = i
        }

        for (i in 1..m) {
            for (j in 1..n) {
                var cost = 0
                if (s1[i - 1] != s2[j - 1]) {
                    cost = 1
                }
                matrix[i][j] = Math.min(Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j] + 1), matrix[i - 1][j - 1] + cost)
                if (i > 1 && j > 1 && s1[i - 1] == s2[j - 2] && s1[i - 2] == s2[j - 1]) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + cost)
                }
            }
        }
        return Pair(matrix[m][n], matrix)
    }

    fun damerauLevenshteinCount(a: CharSequence, b: CharSequence): Int {
        return damerauLevenshtein(a, b).first
    }

    /**
     * According to Wikipedia:
     * <cite>Damerau–Levenshtein distance between two words is the minimum number of operations (consisting of insertions,
     * deletions or substitutions of a single character, or transposition of two adjacent characters) required to change one word into the other</cite>
     */
    fun damerauLevenshtein(a: CharSequence, b: CharSequence): Pair<Int, Array<IntArray>> {
        val aLength = a.length
        val bLength = b.length
        val d = Array(aLength + 1) { IntArray(bLength + 1) }
        val daMap = hashMapOf<Char, Int>()
        val maxDistance = a.length + b.length
        for (i in 0..aLength) {
            d[i][0] = i
        }
        for (j in 0..bLength) {
            d[0][j] = j
        }
        for (i in 1..aLength) {
            var db = 0
            for (j in 1..bLength) {
                val k = daMap.getOrDefault(b[j - 1], 0)
                val l = db
                var cost = 0
                if (a[i - 1] == b[j - 1]) {
                    db = j
                } else {
                    cost = 1
                }
                val substitution = d[i - 1][j - 1] + cost
                val insertion = d[i][j - 1] + 1
                val deletion = d[i - 1][j] + 1
                val transposition = if (k == 0 || l == 0) maxDistance else d[k - 1][l - 1] + (i - k - 1) + 1 + (j - l - 1)
                d[i][j] = intArrayOf(substitution, insertion, deletion, transposition).min() as Int
            }
            daMap[a[i - 1]] = i
        }
        return Pair(d[aLength][bLength], d)
    }
}