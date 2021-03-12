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
        val del = 1000.0
        val points = Array(floor(len * del).toInt()) { i -> i / del + a }
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
         val x1 = points[0].x; val x2 = points[2].x; val x3 = points[1].x
         val fx1 = points[0].y; val fx2 = points[2].y; val fx3 = points[1].y

        val a0 = fx1;
        val a1 = (fx2 - fx1) / (x2 - x1);
        val a2 = 1 / (x3 - x2) * ((fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1));


        val a = -2.0; val b = 3.0
        //val a = x1; val b = x2

        val len = b - a
        val del = 200.0

        val newPoints = Array(floor(len * del).toInt()) { i -> i / del + a }
        newPoints.sort()
        var dataPoints = emptyArray<DataPoint>()
        for (x in newPoints) {
            val y = a2 * x.pow(2.0) + a1 * x + a0
            dataPoints += DataPoint(x, y)
        }

        return dataPoints
    }

}