package com.dizdarevic.grckikino.ui.rounds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dizdarevic.grckikino.databinding.FragmentRoundsBinding
import com.dizdarevic.grckikino.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoundsFragment : Fragment() {
    private lateinit var binding: FragmentRoundsBinding
    private val viewModel: RoundsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoundsBinding.inflate(layoutInflater, container, false)

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
        viewModel.response.observe(viewLifecycleOwner) {
            Log.i(TAG, "responseSuccessful: $it")
        }
        viewModel.get20Rounds()
    }

    companion object {
        private const val TAG = "RoundsFragment"
    }
}