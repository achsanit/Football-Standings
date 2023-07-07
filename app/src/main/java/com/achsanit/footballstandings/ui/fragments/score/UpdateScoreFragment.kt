package com.achsanit.footballstandings.ui.fragments.score

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.achsanit.footballstandings.R
import com.achsanit.footballstandings.databinding.FragmentUpdateScoreBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateScoreFragment : Fragment() {
    private var _binding: FragmentUpdateScoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpdateScoreViewModel by viewModel()
    private var listClubName: List<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListName()
        setButtonState(false)
        with(binding) {
            viewModel.getListName().observe(viewLifecycleOwner) {
                val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, it)
                (tilFirstClub.editText as? MaterialAutoCompleteTextView)?.setAdapter(adapter)
                (tilSecondClub.editText as? MaterialAutoCompleteTextView)?.setAdapter(adapter)
            }

            btnSave.setOnClickListener {
                updateScore()
            }
        }
        inputValidation()
    }

    @SuppressLint("CheckResult")
    private fun inputValidation() {
        with(binding) {
            val firstClubStream = RxTextView.textChanges(spinnerFirstClub)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            firstClubStream.subscribe {
                showFirstClubError(it)
            }

            val secondClubStream = RxTextView.textChanges(spinnerSecondClub)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            secondClubStream.subscribe {
                showSecondClubError(it)
            }

            val firstScoreStream = RxTextView.textChanges(edtScoreFirstClub)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            firstScoreStream.subscribe {
                showFirstScoreError(it)
            }

            val secondScoreStream = RxTextView.textChanges(edtScoreSecondClub)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            secondScoreStream.subscribe {
                showSecondScoreError(it)
            }

            val btnStream = Observable.combineLatest(
                firstClubStream,
                secondClubStream,
                firstScoreStream,
                secondScoreStream
            ) { firsClubInvalid, secondClubInvalid, firstScoreInvalid, secondScoreInvalid ->
                !firsClubInvalid &&
                        !secondClubInvalid &&
                        !firstScoreInvalid &&
                        !secondScoreInvalid
            }
            btnStream.subscribe {
                setButtonState(it)
            }
        }
    }

    private fun updateScore() {
        with(binding) {
            val firstClub = spinnerFirstClub.text.toString()
            val secondClub = spinnerSecondClub.text.toString()
            val firstScore = edtScoreFirstClub.text.toString().toInt()
            val secondScore = edtScoreSecondClub.text.toString().toInt()

            if (firstClub == secondClub) {
                Toast.makeText(
                    requireContext(),
                    "Cant update with the same club",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (firstScore == secondScore) {
                    viewModel.updateScore(
                        firstClub, 1, firstScore, secondScore, 1, 0, 0
                    )
                    viewModel.updateScore(
                        secondClub,1, secondScore, secondScore, 1, 0, 0
                    )
                } else if (firstScore > secondScore) {
                    viewModel.updateScore(
                        firstClub,3,firstScore,secondScore,0,1,0
                    )
                    viewModel.updateScore(
                        secondClub,0,secondScore,firstScore,0,0,1
                    )
                } else {
                    viewModel.updateScore(
                        firstClub,0,firstScore,secondScore,0,0,1
                    )
                    viewModel.updateScore(
                        secondClub,3,secondScore,firstScore,0,1,0
                    )
                }

                Toast.makeText(
                    requireContext(),
                    "Score has been updated...",
                    Toast.LENGTH_SHORT
                ).show()
                edtScoreFirstClub.text?.clear()
                edtScoreSecondClub.text?.clear()
                spinnerFirstClub.text?.clear()
                spinnerSecondClub.text?.clear()
            }
        }
    }

    private fun getListName() {
        viewModel.getListName().observe(viewLifecycleOwner) {
            listClubName = it
            Log.d("list club name", listClubName.toString())
        }
    }

    private fun showFirstClubError(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilFirstClub.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilFirstClub.apply {
                error = null
                isErrorEnabled = false
            }
        }
    }

    private fun showSecondClubError(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilSecondClub.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilSecondClub.apply {
                error = null
                isErrorEnabled = false
            }
        }
    }

    private fun showFirstScoreError(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilScoreFirstClub.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilScoreFirstClub.apply {
                error = null
                isErrorEnabled = false
            }
        }
    }

    private fun showSecondScoreError(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilScoreSecondClub.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilScoreSecondClub.apply {
                error = null
                isErrorEnabled = false
            }
        }
    }

    private fun setButtonState(isEnable: Boolean) {
        if (isEnable) {
            binding.btnSave.apply {
                isEnabled = true
                isClickable = true
                alpha = 1F
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.google.android.material.R.color.design_default_color_primary
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.white
                    )
                )
            }
        } else {
            binding.btnSave.apply {
                isEnabled = false
                isClickable = false
                alpha = 0.5F
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.darker_gray
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.white
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}