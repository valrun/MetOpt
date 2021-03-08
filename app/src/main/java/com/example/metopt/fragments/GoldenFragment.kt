package com.example.metopt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.metopt.R
import com.example.metopt.methods.GoldenRatioMethod
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_golden.view.graph
import kotlin.math.exp
import kotlin.math.pow

class GoldenFragment : Fragment() {
    private var countValue = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_golden, container, false)

        val graph = view.graph as GraphView
        val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>(
            PointsOfMethods().getArray(GoldenRatioMethod { x: Double ->
                x.pow(2.0) + exp(-0.35 * x)
            })
        )
        graph.addSeries(series)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = GoldenFragmentArgs.fromBundle(requireArguments()).count
        //homeCount.text = temp
        countValue = temp.toInt()
    }
}