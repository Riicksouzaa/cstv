package com.codenome.cstv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codenome.cstv.databinding.FragmentMatchesDetailBinding
import com.google.android.material.appbar.MaterialToolbar

class MatchesDetailFragment : Fragment() {

    private var _binding: FragmentMatchesDetailBinding? = null
    private val binding: FragmentMatchesDetailBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.let {
            with(it) {
                isTitleCentered = true
                setTitleTextAppearance(activity, R.style.Toolbar_TitleTextSmall)
            }
        }
    }
}