package com.example.metopt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.navigationadvancedsample.navigate
import com.example.metopt.R
import kotlinx.android.synthetic.main.fragment_dictionary.*
import kotlinx.android.synthetic.main.fragment_dictionary.view.*

class DictionaryFragment  : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_dictionary, container, false)
        view.next.setOnClickListener {
            navigate(DictionaryFragmentDirections.actionDictionaryToNew((countValue + 1).toString()))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = DictionaryFragmentArgs.fromBundle(requireArguments()).count
        //homeCount.text = temp
        countValue = temp.toInt()
    }
}