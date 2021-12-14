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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xyz.teamgravity.aliftech.databinding.FragmentEventListBinding
import xyz.teamgravity.aliftech.presentation.extension.invisible
import xyz.teamgravity.aliftech.presentation.extension.navigateSafely
import xyz.teamgravity.aliftech.presentation.extension.visible
import javax.inject.Inject

@AndroidEntryPoint
class EventListFragment : Fragment(), EventListListener {

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
        adapter.listener = this
        binding.apply {
            recyclerview.addOnScrollListener(listener)
            recyclerview.adapter = adapter
        }
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

    override fun onEventClick(url: String) {
        findNavController().navigateSafely(EventListFragmentDirections.actionEventListFragmentToEventWebFragment(url = url))
    }

    private val listener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!viewmodel.finished && !viewmodel.loading) {
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    viewmodel.onNextPage()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerview.removeOnScrollListener(listener)
        _binding = null
    }
}