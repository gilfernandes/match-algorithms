package org.fernandes.algorithms.fuzzy

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DamerauLevenshteinTest {

    val wagnerDamerau = DamerauLevenshtein()

    @Test
    fun osaDistanceCount() {
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("h1", "h1")).isEqualTo(0)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("gil", "gil")).isEqualTo(0)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("gil", "gill")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("waht", "what")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("waht", "wait")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("waht", "whit")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("what", "wtah")).isEqualTo(3)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("ca", "abc")).isEqualTo(3)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("Damerau", "uameraD")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("tahw", "what")).isEqualTo(2)
    }

    @Test
    fun damerauLevenshteinDistanceCount() {
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("h1", "h1")).isEqualTo(0)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("gil", "gil")).isEqualTo(0)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("gil", "gill")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("ca", "abc")).isEqualTo(2) // Differs from optimal string alignment distance distance
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("waht", "what")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("thaw", "what")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("waht", "wait")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("Damerau", "uameraD")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("Damerau", "Daremau")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.damerauLevenshteinCount("Damerau", "Damreau")).isEqualTo(1)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("waht", "whit")).isEqualTo(2)
        Assertions.assertThat(wagnerDamerau.osaDistanceCount("what", "wtah")).isEqualTo(2)
    }

}