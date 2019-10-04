package nasserkhosravi.la.geometry

import java.text.NumberFormat
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis


fun Boolean.ifTrue(action: () -> Unit) {
    if (this) {
        action()
    }
}

fun Boolean.ifFalse(action: () -> Unit) {
    if (!this) {
        action()
    }
}

fun printMeasureExeTimeMS(action: () -> Unit) {
    val measureExeTime = measureTimeMillis(action)
    println("ms time: $measureExeTime")
}

fun printMeasureExeTimeNano(action: () -> Unit) {
    val measureExeTime = measureNanoTime(action)
    println("ms time: $measureExeTime")
}


fun IntArray.toDouble(): DoubleArray {
    return DoubleArray(size) { index -> this[index].toDouble() }
}

val numberFormat = NumberFormat.getInstance()