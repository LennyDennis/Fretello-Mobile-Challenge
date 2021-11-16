package com.lennydennis.mobilechallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lennydennis.mobilechallenge.data.models.Exercise
import com.lennydennis.mobilechallenge.databinding.ExerciseItemBinding
import android.R
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.Target


class ExerciseAdapter(private val exerciseList: List<Exercise>,context:Context) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    private var context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val exerciseLayoutBinding = ExerciseItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ExerciseViewHolder(exerciseLayoutBinding); }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        val exerciseId = exercise.exerciseId.toInt()
        val imageUrl = "http://codingtest.fretello.com:3000/img/$exerciseId.png"
        val imageView = holder.exerciseBinding.exerciseImage

        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_media_play)
            .into(imageView)
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    inner class ExerciseViewHolder(val exerciseBinding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(exerciseBinding.root) {
        private val exerciseName: TextView = exerciseBinding.exerciserNameTv
        private val bpmPlayed: TextView = exerciseBinding.bpmTv
        fun bind(exercise: Exercise) {
            this.exerciseName.text = exercise.name
            this.bpmPlayed.text = exercise.practicedAtBpm.toString()
        }
    }
}