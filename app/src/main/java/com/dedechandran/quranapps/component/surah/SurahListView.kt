package com.dedechandran.quranapps.component.surah

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.component.bottomsheet.BottomSheetListAdapter

class SurahListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val surahAdapter : SurahListAdapter by lazy {
        SurahListAdapter()
    }

    init {
        super.setAdapter(surahAdapter)
        super.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        super.addItemDecoration(divider)
    }

    fun setItems(items: List<SurahListItem>) {
        surahAdapter.setItems(items)
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        surahAdapter.setOnItemClickListener(onItemClickListener)
    }

}