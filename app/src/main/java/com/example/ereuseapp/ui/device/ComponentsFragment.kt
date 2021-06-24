package com.example.ereuseapp.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ereuseapp.ApiManagement
import com.example.ereuseapp.R
import com.example.ereuseapp.TabInteraction

class ComponentsFragment : Fragment(), TabInteraction {
    private lateinit var scoreProcessor: ProgressBar
    private lateinit var scoreRAM: ProgressBar
    private lateinit var id: String
    private lateinit var processorInfo: TextView
    private lateinit var ramInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_components, container, false)

        processorInfo = view.findViewById(R.id.processorInfo)
        ramInfo= view.findViewById(R.id.ramInfo)

        id = ""

        scoreProcessor = view.findViewById(R.id.scoreProcessor)
        scoreRAM = view.findViewById(R.id.scoreRAM)

        return view
    }

    override fun updateFragmentData(data: String) {
        id = data

        ApiManagement.getDevicesProcessorInfo(id) { processor, s ->
            if (processor != null) {
                processorInfo.text = processor.manufacturer + " " + processor.model
                scoreProcessor.progress = processor.score
            }
        }

        ApiManagement.getDevicesRAMInfo(id) { ram, s ->
            if (ram != null) {
                ramInfo.text = ram.type + " " + ram.info
                scoreRAM.progress = ram.score
            }
        }
    }

}