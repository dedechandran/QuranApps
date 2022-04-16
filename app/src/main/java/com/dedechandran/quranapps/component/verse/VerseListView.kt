package com.dedechandran.quranapps.component.verse

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerseListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val surahAdapter: VerseListAdapter by lazy {
        VerseListAdapter()
    }

    init {
        super.setAdapter(surahAdapter)
        super.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        super.addItemDecoration(divider)
    }

    fun setItems(items: List<VerseListItem>) {
        surahAdapter.setItems(items)
    }

    fun setOnItemClickListener(onItemClickListener: (String) -> Unit) {
        surahAdapter.setOnItemClickListener(onItemClickListener)
    }

}