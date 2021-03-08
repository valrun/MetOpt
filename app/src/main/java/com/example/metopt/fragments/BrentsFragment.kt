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
import com.example.metopt.methods.BrentsMethods
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries.CustomShape
import kotlinx.android.synthetic.main.fragment_brents.view.*
import kotlin.math.exp
import kotlin.math.pow

class BrentsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_brents, container, false)

        val graph = view.graph as GraphView
        graph.getViewport().setScalable(true)
        graph.getViewport().setScalableY(true)

        val points = PointsOfMethods().getArray(BrentsMethods { x: Double ->
            x.pow(2.0) + exp(-0.35 * x)
        })
        var i = 0
        var firstPart = true
        for (point in points) {
            val series =  PointsGraphSeries(point)
            if (firstPart)
            {
                series.color  = Color.rgb(255f * i / points.size,
                    255f * i / points.size,
                    128f + 127f * i / points.size)
                i += 2
            }
            else
            {
                series.color  = Color.rgb(165f + 60f * i / points.size,
                    42f + 183f * i / points.size,
                    42f + 183f * i / points.size)
                i -= 2
            }
            series.setCustomShape(CustomShape { canvas, paint, x, y, dataPoint ->
                paint.strokeWidth = 5F
                canvas.drawCircle(x, y, 12F, paint)
            })
            series.setOnDataPointTapListener { series, dataPoint ->
                Toast.makeText(
                    activity,
                    "Left\n x: ${point[0].x} \n y: ${point[0].y} \n" +
                            "Mid\n x: ${point[1].x} \n y: ${point[1].y} \n" +
                            "Right\n x: ${point[2].x} \n y: ${point[2].y} \n",
                    Toast.LENGTH_SHORT
                ).show()
            }
            graph.addSeries(series)

            if (2 * i > points.size) {
                firstPart = false
            }
        }


        val series2 = LineGraphSeries(
            PointsOfMethods().getFunction()
        )
        series2.color = Color.GRAY
        graph.addSeries(series2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = BrentsFragmentArgs.fromBundle(requireArguments()).count
        countValue = temp.toInt()
    }
}