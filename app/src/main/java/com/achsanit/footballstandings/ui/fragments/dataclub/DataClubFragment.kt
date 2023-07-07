package com.achsanit.footballstandings.ui.fragments.dataclub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.achsanit.footballstandings.R
import com.achsanit.footballstandings.databinding.FragmentDataClubBinding
import com.achsanit.footballstandings.ui.fragments.dataclub.adapter.DataClubAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataClubFragment : Fragment() {
    private var _binding: FragmentDataClubBinding? = null
    private val binding get() = _binding!!

    private val viewModel:DataClubViewModel by viewModel()
    private val clubAdapter: DataClubAdapter by lazy {
        DataClubAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvDataClub.apply {
                adapter = clubAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }

            fabAddDataClub.setOnClickListener {
                findNavController().navigate(R.id.action_nav_data_club_to_addDataClubFragment)
            }

            ivDeleteDataClub.setOnClickListener {
                viewModel.deleteDataClub()
                Toast.makeText(requireContext(), "Data club has been deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        getAllData()
    }

    private fun getAllData() {
        with(binding) {
            viewModel.getAllClub().observe(viewLifecycleOwner) { data ->
                if (data.isNullOrEmpty()) {
                    tableLayout.visibility = View.GONE
                    rvDataClub.visibility = View.GONE
                    tvDataNotFound.visibility = View.VISIBLE
                    ivDataNotFound.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Data Club is Empty", Toast.LENGTH_SHORT).show()
                } else {
                    tableLayout.visibility = View.VISIBLE
                    rvDataClub.visibility = View.VISIBLE
                    tvDataNotFound.visibility = View.GONE
                    ivDataNotFound.visibility = View.GONE

                    clubAdapter.submitData(data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}