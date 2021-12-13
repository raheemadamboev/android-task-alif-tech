package xyz.teamgravity.aliftech.presentation.event_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xyz.teamgravity.aliftech.databinding.FragmentEventListBinding
import xyz.teamgravity.aliftech.presentation.extension.invisible
import xyz.teamgravity.aliftech.presentation.extension.visible
import javax.inject.Inject

@AndroidEntryPoint
class EventListFragment : Fragment() {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<EventListViewModel>()

    @Inject
    lateinit var adapter: EventListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview()
        button()
        observe()
    }

    private fun recyclerview() {
        binding.recyclerview.adapter = adapter
    }

    private fun button() {
        onRetry()
    }

    private fun observe() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewmodel.state.collectLatest { state ->
                        when {
                            state.loading -> {
                                progressBar.visible()
                                retryB.invisible()
                                errorT.invisible()
                            }

                            state.error.isNotBlank() -> {
                                progressBar.invisible()
                                retryB.visible()
                                errorT.text = state.error
                                errorT.visible()
                            }

                            state.events.isNotEmpty() -> {
                                progressBar.invisible()
                                retryB.invisible()
                                errorT.invisible()
                                adapter.submitList(state.events)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onRetry() {
        binding.retryB.setOnClickListener {
            viewmodel.getEvents()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}