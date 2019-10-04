package nasserkhosravi.la.geometry

import nasserkhosravi.la.geometry.model.Mat
import nasserkhosravi.la.geometry.model.Vec

fun Mat.isRectangular(): Boolean {
    return rowSize != colSize
}

fun Mat.isInversable(): Boolean {
    if (isSquare()) {
        throw UnsupportedOperationException()
    } else {
        return false
    }
}

fun Mat.isOrthogonal(){

}

fun Mat.isSymmetric(): Boolean {
    if (!isSquare()) {
        return false
    }
    val mOut = Mat.createEmpty(rowSize, colSize)
    MatOp.transpose(this, mOut)
    return this == mOut
}

fun Vec.toPretty(): String {
    val builder = StringBuilder().append("[")
    data.forEach {
        builder.append(" $it")
    }
    return builder.append(" ]").toString()
}

fun Vec.printPretty() {
    println(toPretty())
}

fun Int.isPositive()  = this > -1


fun Double.deg2Rad(): Double {
    return this * Math.PI / 180
}

fun Double.radToDeg() {
    this * 180 / Math.PI
}