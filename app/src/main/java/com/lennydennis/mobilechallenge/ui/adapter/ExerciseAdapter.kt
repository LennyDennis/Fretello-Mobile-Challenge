package com.lennydennis.mobilechallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennydennis.mobilechallenge.data.models.Exercise
import com.lennydennis.mobilechallenge.databinding.ExerciseItemBinding

class ExerciseAdapter(private val exerciseList: List<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val exerciseLayoutBinding = ExerciseItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ExerciseViewHolder(exerciseLayoutBinding);    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    inner class ExerciseViewHolder(val exerciseBinding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(exerciseBinding.root) {
        private val id: TextView = exerciseBinding.idTv
        private val exerciseName: TextView = exerciseBinding.exerciserNameTv
        private val bpmPlayed: TextView = exerciseBinding.bpmTv
        fun bind(exercise: Exercise) {
            this.id.text = exercise.exerciseId
            this.exerciseName.text = exercise.name
            this.bpmPlayed.text = exercise.practicedAtBpm.toString()
        }
    }
}