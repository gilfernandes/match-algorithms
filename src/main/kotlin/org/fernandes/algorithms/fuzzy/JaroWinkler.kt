package org.fernandes.algorithms.fuzzy

/**
 * Jaro Winkler implementation based on the Apache version.
 */
class JaroWinkler(val defaultScalingFactor: Double = 0.1, val defaultPrefixLength: Int = 4, val percentageRoundValue: Double = 100.0) {

    data class MatchResult(val matches: Int, val transpositions: Int, val prefix: Int)

    data class JaroWinklerResult(val jaro: Double, val jaroWinkler: Double)

    fun apply(left: CharSequence, right: CharSequence): JaroWinklerResult {
        requireNotNull(right) { "right char sequence should not be null" }
        requireNotNull(left) { "left char sequence should not be null" }

        val (matches, transpositions, prefix) = match(left, right)
        val leftLength = left.length
        val rightLength = right.length
        val m: Double = matches.toDouble()
        val jaro: Double = if (matches == 0) 0.0 else
            1 / 3.0 * (m / leftLength + m / rightLength + (m - transpositions) / m)
        val winkler: Double = if (jaro < 0.7) jaro else
            jaro + (prefix * Math.min(defaultScalingFactor, 0.25) * (1.0 - jaro))
        return JaroWinklerResult(round(jaro), jaroWinkler = round(winkler))
    }

    private fun round(jaro: Double) = Math.round(jaro * percentageRoundValue) / percentageRoundValue

    fun match(left: CharSequence, right: CharSequence): MatchResult {
        val rightIsMax = right.length >= left.length
        val max: CharSequence = if (rightIsMax) right else left
        val min: CharSequence = if (rightIsMax) left else right
        val range = Math.max(max.length / 2 - 1, 0)
        val matchIndexes = BooleanArray(min.length)
        val matchFlags = BooleanArray(max.length)
        var matches = 0
        for ((mi, c1) in min.withIndex()) {
            for (xi in Math.max(mi - range, 0)..Math.min(mi + range + 1, max.length) - 1) {
                if (!matchFlags[xi] && c1 == max[xi]) {
                    matchIndexes[mi] = true
                    matchFlags[xi] = true
                    matches += 1
                    break
                }
            }
        }
        val ms1 = CharArray(matches)
        val ms2 = CharArray(matches)
        fillMatchesArray(min, matchIndexes, ms1)
        fillMatchesArray(max, matchFlags, ms2)
        val transpositions = ms1.filterIndexed { i, c -> ms2[i] != c }.count()
        val prefix = min.zip(max).withIndex()
                .takeWhile { (index, value) -> index < defaultPrefixLength && value.first == value.second }
                .count()
        return MatchResult(matches, transpositions / 2, prefix)
    }

    private fun fillMatchesArray(min: CharSequence, matchIndexes: BooleanArray, ms1: CharArray) {
        var si = 0
        (0..min.length - 1)
                .filter { matchIndexes[it] }
                .forEach { ms1[si++] = min[it] }
    }
}