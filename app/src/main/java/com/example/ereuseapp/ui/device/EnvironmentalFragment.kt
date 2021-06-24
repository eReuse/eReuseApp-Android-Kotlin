package com.example.ereuseapp.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ereuseapp.R
import com.example.ereuseapp.TabInteraction

class EnvironmentalFragment : Fragment(), TabInteraction {
    private lateinit var scoreBar: ProgressBar
    private lateinit var textReparability: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_environmental, container, false)

        scoreBar = view.findViewById(R.id.scoreBar)
        textReparability = view.findViewById(R.id.reparability)

        return view
    }

    override fun updateFragmentData(data: String) {
        scoreBar.progress = data.toFloat().toInt()
        textReparability.text = data
    }

}