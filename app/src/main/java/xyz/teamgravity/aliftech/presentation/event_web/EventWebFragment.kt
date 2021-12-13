package xyz.teamgravity.aliftech.presentation.event_web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.aliftech.databinding.FragmentEventWebBinding
import javax.inject.Inject

@AndroidEntryPoint
class EventWebFragment : Fragment() {

    private var _binding: FragmentEventWebBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var client: WebViewClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}