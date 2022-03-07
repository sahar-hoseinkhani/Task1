package ir.ayantech.task.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ir.ayantech.task.databinding.FragmentSearchBinding
import ir.ayantech.task.helper.textChanges
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class SearchFragment : BaseFragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.destinationEt.setText(requireArguments().getString("destination"))

        setupActions()
    }

    private fun setupActions() {
        binding.chooseDateBtn.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.dateTv.text = "$dayOfMonth/$monthOfYear/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.destinationEt.textChanges {
            checkButtonEnableStatus()
            activity?.supportFragmentManager?.setFragmentResult(
                "requestKey",
                bundleOf("bundleKey" to it)
            )
        }

        binding.fromEt.textChanges {
            checkButtonEnableStatus()
        }
    }

    private fun checkButtonEnableStatus() {
        binding.searchBtn.isEnabled = binding.destinationEt.text.toString().isNotEmpty() &&
                binding.fromEt.text.toString().isNotEmpty() &&
                binding.dateTv.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}