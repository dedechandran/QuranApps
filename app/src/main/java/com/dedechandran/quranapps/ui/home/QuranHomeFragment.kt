package com.dedechandran.quranapps.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dedechandran.quranapps.R
import com.dedechandran.quranapps.databinding.FragmentQuranHomeBinding
import com.dedechandran.quranapps.ui.details.QuranDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranHomeFragment : Fragment() {

    private var _binding: FragmentQuranHomeBinding? = null
    private val binding get() = _binding!!

    private val vm: QuranHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_quran_home, container, false)
        _binding = FragmentQuranHomeBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.initialize()

        binding.cvLastRead.setOnClickListener {
            val bundle = bundleOf(
                QuranDetailsFragment.EXTRA_SURAH_NUMBER to vm.lastRead.value?.lastReadSurahNumber,
                QuranDetailsFragment.EXTRA_VERSE_NUMBER to vm.lastRead.value?.lastReadVerseNumber
            )
            findNavController().navigate(
                resId = R.id.action_quranHomeFragment_to_quranDetailsFragment,
                args = bundle
            )
        }

        binding.rvSurah.setOnItemClickListener {
            val bundle = bundleOf(QuranDetailsFragment.EXTRA_SURAH_NUMBER to it)
            findNavController().navigate(
                resId = R.id.action_quranHomeFragment_to_quranDetailsFragment,
                args = bundle
            )
        }

        with(viewLifecycleOwner) {
            vm.displayedItems.observe(this) {
                binding.rvSurah.setItems(items = it)
            }

            vm.isLastReadDisplayed.observe(this) { displayed ->
                binding.cvLastRead.isVisible = displayed
                binding.root.post {
                    binding.toolbarContainer.isLiftOnScroll = !displayed
                    if (displayed) {
                        binding.toolbarContainer.elevation = NOT_ELEVATE
                    } else {
                        binding.toolbarContainer.elevation = ELEVATE
                    }
                }
            }

            vm.isProgressBarDisplayed.observe(this) { displayed ->
                binding.progressBar.isVisible = displayed
            }

            vm.isErrorStateDisplayed.observe(this) { displayed ->
                binding.errorStateContainer.clErrorState.isVisible = displayed
                binding.errorStateContainer.btnRetry.setOnClickListener {
                    vm.getSurahList()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getLastReadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ELEVATE = 1.0F
        private const val NOT_ELEVATE = 0.0F
    }
}