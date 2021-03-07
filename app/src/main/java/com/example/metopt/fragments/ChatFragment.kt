package com.example.metopt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.metopt.R
import androidx.fragment.app.Fragment
import com.example.android.navigationadvancedsample.navigate
import com.example.metopt.methods.BrentsMethods
import com.example.metopt.methods.PointsOfMethods
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import kotlin.math.exp
import kotlin.math.pow

class ChatFragment  : Fragment() {
    private var chatCountNum = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        /*if (chatCount != null) {
            chatCount.text = (chatCountNum).toString()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*if (chatCount != null) {
            chatCount.text = (chatCountNum).toString()
        }*/
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        view.chatButton.setOnClickListener {
            navigate(ChatFragmentDirections.actionCharToNew((chatCountNum + 1).toString()))
        }

        val graph = view.graphChat as GraphView
        val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>(
            PointsOfMethods().getArray(BrentsMethods { x: Double ->
                x.pow(2.0) + exp(-0.35 * x)
            } )
        )
        graph.addSeries(series)

        return view
    }

    /*
    fun countClick(view: View) {
        val chatCountNum = chatCount.text as Int
        chatCount.text = (chatCountNum + 1).toString()
    }
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = ChatFragmentArgs.fromBundle(requireArguments()).count
        chatCount.text = temp
        chatCountNum = temp.toInt()
    }
}