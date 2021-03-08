package com.example.metopt.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.metopt.R
import androidx.fragment.app.Fragment
import com.example.metopt.methods.DichotomyMethod
import com.example.metopt.methods.FibonacciMethod
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import kotlinx.android.synthetic.main.fragment_dichotomy.view.*
import kotlinx.android.synthetic.main.fragment_fibonacci.view.*
import kotlinx.android.synthetic.main.fragment_fibonacci.view.graph
import kotlin.math.exp
import kotlin.math.pow

class FibonacciFragment : Fragment() {
    private var FibonacciCountNum = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        /*if (FibonacciCount != null) {
            FibonacciCount.text = (FibonacciCountNum).toString()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*if (FibonacciCount != null) {
            FibonacciCount.text = (FibonacciCountNum).toString()
        }*/
        val view = inflater.inflate(R.layout.fragment_fibonacci, container, false)

        val graph = view.graph as GraphView
        graph.getViewport().setScalableY(true);

        val points = PointsOfMethods().getArray(FibonacciMethod { x: Double ->
            x.pow(2.0) + exp(-0.35 * x)
        })
        var i = 0;
        for (point in points) {
            val series =  PointsGraphSeries(arrayOf(point))
            series.color = Color.rgb(
                255,
                255 * i / points.size,
                255 * i / points.size
            )
            series.setCustomShape(PointsGraphSeries.CustomShape { canvas, paint, x, y, dataPoint ->
                paint.strokeWidth = 5F
                canvas.drawCircle(x, y, 12F, paint)
            })
            graph.addSeries(series)
            i++
        }

        val series2 = LineGraphSeries(
            PointsOfMethods().getFunction()
        )
        series2.color = Color.GRAY
        graph.addSeries(series2)
        return view
    }

    /*
    fun countClick(view: View) {
        val FibonacciCountNum = FibonacciCount.text as Int
        FibonacciCount.text = (FibonacciCountNum + 1).toString()
    }
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = FibonacciFragmentArgs.fromBundle(requireArguments()).count
        //FibonacciCount.text = temp
        FibonacciCountNum = temp.toInt()
    }
}