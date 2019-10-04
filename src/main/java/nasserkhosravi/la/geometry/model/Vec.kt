package nasserkhosravi.la.geometry.model

import nasserkhosravi.la.geometry.OneDimIterator
import nasserkhosravi.la.geometry.create1dMat

open class Vec() : Iterable<Double> {
    lateinit var data: DoubleArray

    override fun iterator() = OneDimIterator(data)

    operator fun get(v: Int): Double {
        return data[v]
    }

    operator fun set(i: Int, value: Double) {
        data[i] = value
    }

    operator fun timesAssign(scalar: Int) {
        for (i in data.indices) {
            data[i] = scalar * data[i]
        }
    }

    fun sumUp(): Double {
        return sum()
    }

    constructor(data: Array<Double>) : this() {
        this.data = data.toDoubleArray()
    }

    constructor(data: DoubleArray) : this() {
        this.data = data
    }

    constructor(vararg data: Int) : this() {
        this.data = DoubleArray(data.size) { index -> data[index].toDouble() }
    }

    operator fun plus(m: Vec): Vec {
        require(data.size == m.data.size)
        val arr = DoubleArray(data.size) { index ->
            data[index] + m[index]
        }
        return Vec(arr)
    }

    operator fun times(m: Vec): Vec {
        return multiple(m)
    }

    operator fun times(scalar: Int): Vec {
        for (i in data.indices) {
            data[i] = data[i] * scalar
        }
        return this
    }

    operator fun times(scalar: Double): Vec {
        for (i in data.indices) {
            data[i] = data[i] * scalar
        }
        return this
    }

    operator fun div(v: Double): Vec {
        for (i in data.indices) {
            data[i] = data[i] / v
        }
        return this
    }

    fun multiple(m: Vec): Vec {
        require(data.size == m.data.size)
        val arr = Array(data.size) { index -> data[index] * m[index] }
        return Vec(arr)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }


    companion object {

        fun outer(from: Vec, to: Vec): Mat {
            val result = DoubleArray(from.data.size * to.data.size)
            var indexResult = 0
            from.data.forEach {
                for (element in to.data) {
                    result[indexResult] = it * element
                    indexResult++
                }
            }
            return create1dMat(from.data.size, to.data.size, result)
        }
    }

}

