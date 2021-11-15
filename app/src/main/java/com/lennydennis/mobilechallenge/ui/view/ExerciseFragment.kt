package com.lennydennis.mobilechallenge.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lennydennis.mobilechallenge.data.models.Exercise
import com.lennydennis.mobilechallenge.data.models.Session
import com.lennydennis.mobilechallenge.databinding.FragmentExerciseBinding
import com.lennydennis.mobilechallenge.ui.adapter.ExerciseAdapter

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private var session: Session? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val sessionClicked = ExerciseFragmentArgs.fromBundle(bundle!!)
        sessionClicked.session?.let {
            (activity as MainActivity).setToolbarTitle(it.name)
            setUpRecyclerView(it.exercises)
        }
    }

    private fun setUpRecyclerView(exerciseList: List<Exercise>){
        val exerciseListAdapter = ExerciseAdapter(exerciseList)
        binding.exerciseRv.layoutManager = LinearLayoutManager(context)
        binding.exerciseRv.adapter = exerciseListAdapter

    }

}