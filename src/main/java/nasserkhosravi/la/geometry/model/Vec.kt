package nasserkhosravi.la.geometry.model

import java.util.*

open class Vec() : MatAbstract() {
    companion object {
        fun plusArray(list: ArrayList<Vec>, size: Int): Array<Double> {
            val res = Array(size) { 0.0 }
            list.forEachIndexed { index,it->
                it.data.forEach {
                    res[index] = it
                }
            }
            return res
        }

        //sum all elements
        fun sumUp(v1: Vec): Double {
            //todo: use reduce
            var result = 0.0
            v1.data.forEach {
                result += (it)
            }
            return result
        }

        fun outer(from: Vec, to: Vec): Mat {
            val result = Array(from.data.size * to.data.size) { 0.0 }
            var indexResult = 0
            from.data.forEach {
                for (i in 0 until to.data.size) {
                    result[indexResult] = it * to[i]
                    indexResult++
                }
            }
            return Mat.create(from.data.size, to.data.size, result)
        }

    }

    fun sumUp(): Double {
        return sumUp(this)
    }

    constructor(data: Array<Double>) : this() {
        this.data = data
    }

    override fun toString(): String {
        return "Vec(data=${Arrays.toString(data)})"
    }

    operator fun plus(m: Vec): Vec {
        if (data.size != m.data.size) {
            throw IllegalArgumentException("un homogenise size")
        }
        val arr = Array(data.size) { 0.0 }
        for (i in 0 until data.size) {
            arr[i] = data[i] + m[i]
        }
        return Vec(arr)
    }

    operator fun times(m: Vec): Vec {
        if (data.size != m.data.size) {
            throw IllegalArgumentException("un homogenise size")
        }
        val arr = Array(data.size) { 0.0 }
        for (i in 0 until data.size) {
            arr[i] = data[i] * m[i]
        }
        return Vec(arr)
    }

}