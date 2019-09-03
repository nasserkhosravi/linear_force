package nasserkhosravi.la.geometry.model

class Vec3d() : Vec() {

    companion object {
        fun magnitude(v: Vec3d): Double {
            return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2])
        }

        fun normalize(v: Vec3d) {
            val length = magnitude(v)
            v[0] = v[0] / length
            v[1] = v[1] / length
            v[2] = v[2] / length
        }

        fun dotProduct(v1: Vec3d, v2: Vec3d): Double {
            return v1.x() * v2.x() + v1.y() * v2.y() + v1.z() * v2.z()
        }

        fun crossProduct(v1: Vec3d, v2: Vec3d): Vec3d {
            val x = (v1.y() * v2.z()) - (v1.z() * v2.y())
            val y = (v1.z() * v2.x()) - (v1.x() * v2.z())
            val z = (v1.x() * v2.y()) - (v1.y() * v2.x())
            return Vec3d(x, y, z)
        }

    }

    init {
        data = arrayOf(0.0, 0.0, 0.0)
    }

    constructor(data: Array<Double>) : this() {
        if (data.size > 3) {
            throw IllegalArgumentException("data has more than 3 member")
        }
        this.data = data
    }

    constructor(x: Double, y: Double, z: Double) : this() {
        data = arrayOf(x, y, z)
    }

    constructor(x: Int, y: Int, z: Int) : this() {
        data = arrayOf(x.toDouble(), y.toDouble(), z.toDouble())
    }

    fun xyz(x: Double, y: Double, z: Double) {
        data[0] = x
        data[1] = y
        data[2] = z
    }

    fun x(): Double {
        return data[0]
    }

    fun y(): Double {
        return data[1]
    }

    fun z(): Double {
        return data[2]
    }

    fun x(x: Double) {
        data[0] = x
    }

    fun y(y: Double) {
        data[1] = y
    }

    fun z(z: Double) {
        data[2] = z
    }

    fun magnitude(): Double {
        return magnitude(this)
    }

    fun length(): Double {
        return magnitude()
    }

    fun norm(): Double {
        return magnitude()
    }


    fun normalize() {
        normalize(this)
    }

    fun crossProductTo(to: Vec3d): Vec3d {
        return crossProduct(this, to)
    }

    fun dotProductTo(to: Vec3d): Double {
        return dotProduct(this, to)
    }

    override fun toString(): String {
        return "x:${x()} y: ${y()} z: ${z()}"
    }


}