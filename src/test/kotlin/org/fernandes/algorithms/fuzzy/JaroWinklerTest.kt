package org.fernandes.algorithms.fuzzy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.assertj.core.api.Assertions.offset

internal class JaroWinklerTest {

    val jaroWinkler = JaroWinkler()

    @Test
    @DisplayName("when match should have the right score")
    fun whenMatchShouldHaveRightScore() {
        assertThat(jaroWinkler.apply("Roaster", "roasteri").jaroWinkler).isEqualTo(0.87, offset(0.01))
        assertThat(jaroWinkler.apply("roaster", "roasteri").jaroWinkler).isEqualTo(0.98, offset(0.01))
    }
}
