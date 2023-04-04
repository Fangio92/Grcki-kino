package com.dizdarevic.grckikino.ui.rounds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dizdarevic.grckikino.databinding.FragmentRoundsBinding
import com.dizdarevic.grckikino.repo.model.GrckiKino
import com.dizdarevic.grckikino.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoundsFragment : Fragment() {
    private lateinit var binding: FragmentRoundsBinding
    private val viewModel: RoundsViewModel by viewModel()
    private val adapter = RoundsAdapter { onRoundClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoundsBinding.inflate(layoutInflater, container, false)
        binding.rvRounds.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.errors.observe(viewLifecycleOwner) {
            Log.e(TAG, "errors: $it")
        }
        viewModel.response.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.get20Rounds()
    }

    private fun onRoundClick(kinoItem: GrckiKino.GrckiKinoItem) {
        findNavController().navigate(RoundsFragmentDirections.toTalonFragment(kinoItem.drawId))
    }

    companion object {
        private const val TAG = "RoundsFragment"
    }
}