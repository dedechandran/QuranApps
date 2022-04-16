package com.dedechandran.quranapps.component.bottomsheet

sealed class BottomSheetListItem {
    data class Text(
        val text: String
    ): BottomSheetListItem()

}