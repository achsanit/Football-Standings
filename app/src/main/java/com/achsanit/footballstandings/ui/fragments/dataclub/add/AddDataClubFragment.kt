package com.achsanit.footballstandings.ui.fragments.dataclub.add

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.achsanit.footballstandings.R
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.databinding.FragmentAddDataClubBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Compiler.disable
import java.lang.Compiler.enable

class AddDataClubFragment : Fragment() {
    private var _binding: FragmentAddDataClubBinding? = null
    private val binding: FragmentAddDataClubBinding get() = _binding!!

    private val viewModel: AddDataClubViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDataClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonState(false)
        inputValidation()
        with(binding) {
            btnSave.setOnClickListener {
                checkDataIsExist()
            }
        }
    }

    private fun checkDataIsExist() {
        with(binding) {
            val clubName = edtClubName.text.toString()
            val clubCity = edtClubCity.text.toString()
            viewModel.checkClubData(clubName,clubCity).observe(viewLifecycleOwner) {
                it?.let {
                    Toast.makeText(requireContext(), "Data is exist, please add new data", Toast.LENGTH_SHORT).show()
                } ?: run {
                    val data = ClubEntity(
                        clubName = clubName,
                        clubCity = clubCity
                    )

                    viewModel.addDataClub(data)
                    Toast.makeText(requireContext(), "Data has been added", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }

        }
    }

    @SuppressLint("CheckResult")
    private fun inputValidation() {
        with(binding) {
            val nameClubStream = RxTextView.textChanges(edtClubName)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            nameClubStream.subscribe {
                showNameClubAlert(it)
            }

            val cityStream = RxTextView.textChanges(edtClubCity)
                .skipInitialValue()
                .map {
                    it.trim().isEmpty()
                }
            cityStream.subscribe {
                showCityAlert(it)
            }

            val btnStream = Observable.combineLatest(
                nameClubStream,
                cityStream,
            ) { nameClubInvalid, cityInvalid ->
                !nameClubInvalid && !cityInvalid
            }
            btnStream.subscribe {
                setButtonState(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNameClubAlert(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilClubName.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilClubName.apply {
                error = null
                isErrorEnabled = false
            }
        }
    }

    private fun showCityAlert(isNotValid: Boolean) {
        if (isNotValid) {
            binding.tilClubCity.error = getString(R.string.message_field_cant_empty)
        } else {
            binding.tilClubCity.apply {
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
}