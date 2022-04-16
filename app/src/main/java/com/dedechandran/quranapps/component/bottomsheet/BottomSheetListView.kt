package com.dedechandran.quranapps.component.bottomsheet

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.component.surah.SurahListItem

class BottomSheetListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val bottomSheetListAdapter : BottomSheetListAdapter by lazy {
        BottomSheetListAdapter()
    }

    init {
        super.setAdapter(bottomSheetListAdapter)
        super.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        super.addItemDecoration(divider)
    }

    fun setItems(items: List<BottomSheetListItem>) {
        bottomSheetListAdapter.setItems(items)
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        bottomSheetListAdapter.setOnItemClickListener(onItemClickListener)
    }

}