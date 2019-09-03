package nasserkhosravi.la.geometry.model

import java.util.*

abstract class MatAbstract {
    lateinit var data: Array<Double>

    fun scale(scalar: Int) {
        Mat.scale(this, scalar)
    }

    operator fun get(v: Int): Double {
        return data[v]
    }

    operator fun timesAssign(scalar: Int) {
        scale(scalar)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatAbstract

        if (!Arrays.equals(data, other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(data)
    }

    override fun toString(): String {
        return "MatAbstract(data=${Arrays.toString(data)})"
    }

    operator fun set(i: Int, value: Double) {
        data[i] = value
    }

    fun log() {
        println(toString())
    }
}