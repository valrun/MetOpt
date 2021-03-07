package com.example.metopt.methods

import com.jjoe64.graphview.series.DataPoint
import kotlin.math.exp
import kotlin.math.pow

class PointsOfMethods {
     fun getArray(met: AbstractMethod) : Array<DataPoint>{

        /*val met = DichotomyMethod { x: Double ->
            x.pow(2.0) + exp(-0.35 * x)
        } */

        var points = met.getAllIteration(-2.0, 3.0)
        points.sort()
        var dataPoints = emptyArray<DataPoint>()
        for (x in points) {
            val y = x.pow(2.0) + exp(-0.35 * x)
            dataPoints += DataPoint(x, y)
        }

        return dataPoints
    }
}