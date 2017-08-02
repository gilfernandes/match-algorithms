package org.fernandes.algorithms.fuzzy

import org.fernandes.algorithms.EditDistance
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName

/**
 * Created by Gil on 27/07/2017.
 */
internal class EditDistanceTest {

    val editDistance : EditDistance = EditDistance()

    @Test
    @DisplayName("when distance count recursive should have expected results")
    fun whenCountRecursive_ShouldHaveExpectedResults() {
        assertThat(editDistance.count("h1", "h1")).isEqualTo(0)
        assertThat(editDistance.count("gil", "gill")).isEqualTo(1)
        assertThat(editDistance.count("ggil", "gil")).isEqualTo(1)
        assertThat(editDistance.count("ggil", "ggiil")).isEqualTo(1)
        assertThat(editDistance.count("Fernandes", "Hernandis")).isEqualTo(2)
        assertThat(editDistance.count("This is a long text", "Thisis a long tex")).isEqualTo(2)
        assertThat(editDistance.count("Pseudocod for edit distance can be ritten using recursion easily",
                "seudocode fo edit distance can be written using recursion easily")).isEqualTo(4)
        assertThat(editDistance.count("CRATE", "TRACE")).isEqualTo(2)
    }

    @Test
    @DisplayName("when distance count should have expected results")
    fun whenCount_ShouldHaveExpectedResults() {
        assertThat(editDistance.countDynamic("h1", "h1")).isEqualTo(0)
        assertThat(editDistance.countDynamic("gil", "gill")).isEqualTo(1)
        assertThat(editDistance.countDynamic("waht", "what")).isEqualTo(2)
    }

}