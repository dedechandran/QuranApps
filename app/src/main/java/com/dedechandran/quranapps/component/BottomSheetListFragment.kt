package com.dedechandran.quranapps.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dedechandran.quranapps.R
import com.dedechandran.quranapps.component.bottomsheet.BottomSheetListItem
import com.dedechandran.quranapps.databinding.FragmentBottomSheetListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetListFragment : BottomSheetDialogFragment() {

    private lateinit var data: List<BottomSheetListItem>
    private lateinit var onItemClickListener: (Int) -> Unit
    private lateinit var binding: FragmentBottomSheetListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_list, container, false)
        binding = FragmentBottomSheetListBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBottomSheet.apply {
            setItems(data)
            setOnItemClickListener {
                onItemClickListener.invoke(it)
                dismiss()
            }
        }
    }



    companion object {
        fun newInstance(
            data: List<BottomSheetListItem>,
            onItemClickListener: (Int) -> Unit
        ): BottomSheetListFragment {
            return BottomSheetListFragment().apply {
                this.data = data
                this.onItemClickListener = onItemClickListener
            }
        }
    }
}