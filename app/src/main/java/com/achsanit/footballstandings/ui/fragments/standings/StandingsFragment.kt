package com.achsanit.footballstandings.ui.fragments.standings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.achsanit.footballstandings.R
import com.achsanit.footballstandings.databinding.FragmentStandingsBinding
import com.achsanit.footballstandings.ui.fragments.standings.adapter.StandingsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StandingsFragment : Fragment() {

    private var _binding: FragmentStandingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StandingsViewModel by viewModel()
    private val standingsAdapter: StandingsAdapter by lazy {
        StandingsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvStandings.apply {
                adapter = standingsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ivResetStandings.setOnClickListener {
                viewModel.resetStandings()
                Toast.makeText(requireContext(), "standings has been reset", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        getStandings()
    }

    private fun getStandings() {
        viewModel.getStandings().observe(viewLifecycleOwner) {
            with(binding) {
                if (it.isNullOrEmpty()) {
                    rvStandings.visibility = View.GONE
                    tlHead.visibility = View.GONE
                } else {
                    rvStandings.visibility = View.VISIBLE
                    tlHead.visibility = View.VISIBLE
                    standingsAdapter.submitData(it)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}