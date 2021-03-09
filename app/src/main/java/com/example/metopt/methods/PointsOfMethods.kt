package com.example.metopt.methods

import com.jjoe64.graphview.series.DataPoint
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.pow

class PointsOfMethods {
    fun getArray(met: AbstractMethod): Array<Array<DataPoint>> {
        /*val met = DichotomyMethod { x: Double ->
            x.pow(2.0) + exp(-0.35 * x)
        } */
        val points = met.getAllIteration(-2.0, 3.0)
        var dataPoints = emptyArray<Array<DataPoint>>()

        for (arr in points) {
            var tmp = emptyArray<DataPoint>()
            for (x in arr) {
                val y = x.pow(2.0) + exp(-0.35 * x)
                tmp += DataPoint(x, y)
            }
            dataPoints += tmp
        }

        return dataPoints
    }

    fun getFunction(a: Double, b: Double): Array<DataPoint> {
        val len = b - a
        val points = Array(floor(len * 1000).toInt()) { i -> i * 0.001 + a }
        points.sort()
        var dataPoints = emptyArray<DataPoint>()
        for (x in points) {
            val y = x.pow(2.0) + exp(-0.35 * x)
            dataPoints += DataPoint(x, y)
        }

        return dataPoints
    }

    fun getParabole(points: Array<DataPoint>): Array<DataPoint> {
        if (points.size != 3) {
            return emptyArray()
        }
        val k = (points[0].y - points[2].y) / (points[0].x - points[2].x).pow(2.0)
        val y0 = points[2].y
        val x0 = points[2].x

        val a = -2.0
        val b = 3.0
        val len = b - a
        val newPoints = Array(floor(len * 1000).toInt()) { i -> i * 0.001 + a }
        newPoints.sort()
        var dataPoints = emptyArray<DataPoint>()
        for (x in newPoints) {
            val y = k * (x - x0).pow(2.0) + y0
            dataPoints += DataPoint(x, y)
        }

        return dataPoints
    }

}