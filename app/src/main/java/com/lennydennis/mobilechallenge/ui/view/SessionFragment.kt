package com.lennydennis.mobilechallenge.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lennydennis.mobilechallenge.data.models.Session
import com.lennydennis.mobilechallenge.databinding.FragmentSessionBinding
import com.lennydennis.mobilechallenge.util.NetworkResult
import com.lennydennis.mobilechallenge.viewmodel.SessionViewModel
import kotlin.math.roundToInt

class SessionFragment : Fragment() {

    private lateinit var binding: FragmentSessionBinding
    private val sessionViewModel: SessionViewModel by viewModels()
    private var session = listOf<Session>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setToolbarTitle("Mobile Challenge")
        sessionViewModel.getSessions()
        binding.progressBar.visibility = View.VISIBLE
        sessionViewModel.sessionResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    session = result.data
                    calculateIncrease(session)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun calculateIncrease(sessionList: List<Session>){
        var largestIncrease = 0
        for(session in sessionList){
            val currentIndex = sessionList.indexOf(session)
            if(currentIndex < sessionList.size-1){
                val nextSession = sessionList[currentIndex+1]
                var totalBPM = 0f
                session.exercises.forEach {
                    totalBPM += it.practicedAtBpm
                }
                val averageBPM: Float = (totalBPM/session.exercises.size).toFloat()
                nextSession.exercises.forEach{
                    val increase = ((it.practicedAtBpm * 100)/averageBPM).roundToInt()
                    if(increase > largestIncrease){
                        largestIncrease = increase
                    }
                }
            }
        }
        binding.maxTv.text = largestIncrease.toString() + "%"
    }

}