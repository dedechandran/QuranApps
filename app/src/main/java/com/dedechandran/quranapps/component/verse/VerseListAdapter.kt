package com.dedechandran.quranapps.component.verse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.R

class VerseListAdapter : RecyclerView.Adapter<VerseListViewHolder>() {

    private val items = mutableListOf<VerseListItem>()
    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VERSE_ITEM -> VerseListViewHolder.VerseViewHolder(
                binding = DataBindingUtil.inflate(layoutInflater, R.layout.verse_item, parent, false),
                onItemClickListener = onItemClickListener
            )
            BISMILLAH_ITEM -> VerseListViewHolder.BismillahViewHolder(
                binding = DataBindingUtil.inflate(layoutInflater, R.layout.bismillah_item, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: VerseListViewHolder, position: Int) {
        when (holder) {
            is VerseListViewHolder.VerseViewHolder -> holder.bind(items[position] as VerseListItem.Value)
            is VerseListViewHolder.BismillahViewHolder -> holder.bind(items[position] as VerseListItem.Bismillah)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is VerseListItem.Value -> VERSE_ITEM
            is VerseListItem.Bismillah -> BISMILLAH_ITEM
        }
    }

    fun setItems(items: List<VerseListItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: (String) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    companion object {
        const val VERSE_ITEM = 1
        const val BISMILLAH_ITEM = 2
    }

}