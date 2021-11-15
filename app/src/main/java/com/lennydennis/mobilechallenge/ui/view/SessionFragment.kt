package com.lennydennis.mobilechallenge.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lennydennis.mobilechallenge.R
import com.lennydennis.mobilechallenge.data.models.Session
import com.lennydennis.mobilechallenge.databinding.FragmentSessionBinding
import com.lennydennis.mobilechallenge.ui.adapter.SessionAdapter
import com.lennydennis.mobilechallenge.util.NetworkResult
import com.lennydennis.mobilechallenge.viewmodel.SessionViewModel

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

        (activity as MainActivity).setToolbarTitle("Exercise Groups")
        sessionViewModel.getSessions()
        binding.progressBar.visibility = View.VISIBLE
        sessionViewModel.sessionResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.data.sortByDescending { it.practicedOnDate }
                    session = result.data
                    setUpRecyclerView(session)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpRecyclerView(sessionList: List<Session>) {
        binding.progressBar.visibility = View.GONE
        val sessionListAdapter = SessionAdapter(sessionList)
        binding.sessionRv.layoutManager = LinearLayoutManager(context)
        binding.sessionRv.adapter = sessionListAdapter

        sessionListAdapter.sessionClickListener = object : SessionAdapter.SessionClickListener {
            override fun onSessionClicked(session: Session) {
                val session = SessionFragmentDirections.actionSessionFragmentToExerciseFragment(session)
                findNavController().navigate(session)
            }
        }
    }
}