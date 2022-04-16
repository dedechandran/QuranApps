package com.dedechandran.quranapps.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dedechandran.quranapps.R
import com.dedechandran.quranapps.component.BottomSheetListFragment
import com.dedechandran.quranapps.component.bottomsheet.BottomSheetListItem
import com.dedechandran.quranapps.databinding.FragmentQuranDetailsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranDetailsFragment : Fragment() {

    private val vm: QuranDetailsViewModel by viewModels()
    private var _binding: FragmentQuranDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quran_details, container, false)
        _binding = FragmentQuranDetailsBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val surahNumber = arguments?.getInt(EXTRA_SURAH_NUMBER) ?: DEFAULT_INT_ARGUMENT
        val verseNumber = arguments?.getInt(EXTRA_VERSE_NUMBER) ?: DEFAULT_INT_ARGUMENT
        vm.initialize(surahNumber)

        binding.ivArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvVerse.setOnItemClickListener { selectedVerseNumber ->
            BottomSheetListFragment.newInstance(
                data = listOf(BottomSheetListItem.Text("Tandai sebagai terakhir baca")),
                onItemClickListener = {
                    vm.onSetLastReadClicked(
                        verseNumber = selectedVerseNumber.toInt(),
                        timeStamp = System.currentTimeMillis()
                    )
                }
            ).show(childFragmentManager, "BottomSheetListFragment")
        }

        with(viewLifecycleOwner) {
            vm.verses.observe(this) {
                binding.rvVerse.setItems(it)
                if (verseNumber != DEFAULT_INT_ARGUMENT) {
                    binding.rvVerse.smoothScrollToPosition(verseNumber)
                }
            }

            vm.onSuccessAddLastReadVerseEvent.observe(this) { message ->
                Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
            }

            vm.isProgressBarDisplayed.observe(this) { displayed ->
                binding.progressBarCard.isVisible = displayed
                binding.progressBarList.isVisible = displayed
            }

            vm.isErrorStateDisplayed.observe(this) { displayed ->
                binding.errorStateContainer.clErrorState.isVisible = displayed
                binding.tvCardErrorMessage.isVisible = displayed
                binding.errorStateContainer.btnRetry.setOnClickListener {
                    vm.initialize(surahNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_SURAH_NUMBER = "extra_surah_number"
        const val EXTRA_VERSE_NUMBER = "extra_verse_number"
        private const val DEFAULT_INT_ARGUMENT = -1
    }
}