package com.dizdarevic.grckikino.ui.talon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.dizdarevic.grckikino.databinding.FragmentTalonBinding
import com.dizdarevic.grckikino.utils.observe
import kotlinx.datetime.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TalonFragment : Fragment() {
    private lateinit var binding: FragmentTalonBinding
    private val viewModel: TalonViewModel by viewModel()
    private val args: TalonFragmentArgs by navArgs()
    private val adapter = TalonAdapter { onTalonItemClicked() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTalonBinding.inflate(layoutInflater, container, false)
        binding.rvRound.adapter = adapter
        binding.rvRound.layoutManager = GridLayoutManager(requireContext(), 10)
        binding.btDraw.setOnClickListener { openWebView() }
        return binding.root
    }

    private fun openWebView() =
        findNavController().navigate(TalonFragmentDirections.toWebViewFragment())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.errors.observe(viewLifecycleOwner) {
            Log.e(TAG, "errors: $it")
        }
        viewModel.response.observe(viewLifecycleOwner) {
            if (it != null) {
                val roundStartDateTime = Instant.fromEpochMilliseconds(it.drawTime).toLocalDateTime(TimeZone.currentSystemDefault())
                val duration = roundStartDateTime.toInstant(TimeZone.currentSystemDefault()).minus(Clock.System.now())
                binding.tvTime.text = roundStartDateTime.time.toString()
                binding.tvDuration.text = String.format("%02d:%02d", duration.toDateTimePeriod().hours, duration.toDateTimePeriod().minutes)
                binding.tvRoundNumber.text = it.drawId.toString()
            }
        }
        viewModel.getRound(args.drawId)
    }
    private fun onTalonItemClicked() {
        binding.tvTotalnumber.text = adapter.list.filter { it.selected }.size.toString()
    }
    companion object {
        private const val TAG = "TalonFragment"
    }
}