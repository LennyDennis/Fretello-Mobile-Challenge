package com.lennydennis.mobilechallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennydennis.mobilechallenge.data.models.Session
import com.lennydennis.mobilechallenge.databinding.SessionItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SessionAdapter(private val sessionList: List<Session>) :
    RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    lateinit var sessionClickListener: SessionClickListener

    interface SessionClickListener {
        fun onSessionClicked(session: Session)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sessionLayoutBinding = SessionItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return SessionViewHolder(sessionLayoutBinding);    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = sessionList[position]
        holder.sessionBinding.exerciseBtn.setOnClickListener {
            sessionClickListener.onSessionClicked(sessionList[position])
        }
        holder.bind(session)
    }

    override fun getItemCount(): Int {
        return sessionList.size
    }

    inner class SessionViewHolder(val sessionBinding: SessionItemBinding) :
        RecyclerView.ViewHolder(sessionBinding.root) {
        private val sessionName: TextView = sessionBinding.sessionNameTv
        private val sessionDate: TextView = sessionBinding.sessionDateTv
        fun bind(session: Session) {
            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val output = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            var date: Date? = null
            try {
                date = input.parse(session.practicedOnDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val formatted = output.format(date)
            this.sessionName.text = session.name
            this.sessionDate.text = formatted
        }
    }
}