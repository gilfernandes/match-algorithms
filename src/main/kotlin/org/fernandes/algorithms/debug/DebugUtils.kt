package org.fernandes.algorithms.debug

/**
 * Created by Gil on 27/07/2017.
 */
object DebugUtils {

    fun printOut(a: Array<IntArray>) {
        for (row in a) {
            for (col in row) {
                print("${col} ")
            }
            println()
        }
        println()
    }
}