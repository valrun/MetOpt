package com.example.metopt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.navigationadvancedsample.navigate
import com.example.metopt.R
import com.example.metopt.methods.DichotomyMethod
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_brents.*
import kotlinx.android.synthetic.main.fragment_brents.view.*
import kotlin.math.exp
import kotlin.math.pow

class BrentsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_brents, container, false)
        /*view.next.setOnClickListener {
            navigate(BrentsragmentDirections.actionHomeToNew((countValue + 1).toString()))
        }*/

        val graph = view.graph as GraphView
        val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>(
            PointsOfMethods().getArray(DichotomyMethod { x: Double ->
                x.pow(2.0) + exp(-0.35 * x)
            } )
        )
        graph.addSeries(series)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = BrentsFragmentArgs.fromBundle(requireArguments()).count
        //homeCount.text = temp
        countValue = temp.toInt()
    }
}