package com.codenome.cstv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codenome.cstv.databinding.FragmentMatchesBinding
import com.google.android.material.appbar.MaterialToolbar

class MatchesFragment : Fragment() {
    private var _binding: FragmentMatchesBinding? = null
    private val binding: FragmentMatchesBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.let {
            with(it) {
                isTitleCentered = false
                setTitleTextAppearance(activity, R.style.Toolbar_TitleText)
            }
        }

        binding.texte.setOnClickListener {
            findNavController().navigate(R.id.action_MatchesFragment_toMatchesDetailFragment)
        }
    }

}