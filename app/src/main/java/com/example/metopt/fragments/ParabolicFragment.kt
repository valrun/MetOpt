package com.example.metopt.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.metopt.R
import com.example.metopt.methods.ParabolicMethod
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import kotlinx.android.synthetic.main.fragment_parabolic.view.graph
import kotlin.math.exp
import kotlin.math.pow

class ParabolicFragment : Fragment() {
    private var countValue = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_parabolic, container, false)

        val graph = view.graph as GraphView
        graph.getViewport().setScalable(true)
        graph.getViewport().setScalableY(true)

        val series2 = LineGraphSeries(
            PointsOfMethods().getFunction(-2.0, 3.0)
        )
        series2.color = Color.GRAY
        graph.addSeries(series2)

        val points = PointsOfMethods().getArray(ParabolicMethod { x: Double ->
            x.pow(2.0) + exp(-0.35 * x)
        })
        var i = 0
        var firstPart = true
        for (point in points) {
            val series = PointsGraphSeries(point)
            if (firstPart) {
                series.color = Color.rgb(
                    255f * i / points.size,
                    255f * i / points.size,
                    128f + 127f * i / points.size
                )
                i += 2
            } else {
                series.color = Color.rgb(
                    165f + 60f * i / points.size,
                    42f + 183f * i / points.size,
                    42f + 183f * i / points.size
                )
                i -= 2
            }
            series.size = 12f
            series.setOnDataPointTapListener { series, dataPoint ->
                Toast.makeText(
                    activity,
                    "Left\n x: ${point[0].x} \n y: ${point[0].y} \n" +
                            "Current Answer\n x: ${point[1].x} \n y: ${point[1].y} \n" +
                            "Right\n x: ${point[2].x} \n y: ${point[2].y} \n",
                    Toast.LENGTH_SHORT
                ).show()
            }
            graph.addSeries(series)

            val series3 = LineGraphSeries(
                PointsOfMethods().getParabole(point)
            )
            series3.color = series.color
            graph.addSeries(series3)

            if (2 * i > points.size) {
                firstPart = false
            }
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = ParabolicFragmentArgs.fromBundle(requireArguments()).count
        //homeCount.text = temp
        countValue = temp.toInt()
    }
}