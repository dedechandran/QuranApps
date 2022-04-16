package com.dedechandran.quranapps.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.dedechandran.quranapps.common.Result
import com.dedechandran.quranapps.common.SingleLiveEvent
import com.dedechandran.quranapps.common.toDate
import com.dedechandran.quranapps.component.surah.SurahListItem
import com.dedechandran.quranapps.domain.GetLastReadUseCase
import com.dedechandran.quranapps.domain.GetSurahUseCase
import com.dedechandran.quranapps.domain.LastRead
import com.dedechandran.quranapps.domain.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranHomeViewModel @Inject constructor(
    private val getSurahUseCase: GetSurahUseCase,
    private val getLastReadUseCase: GetLastReadUseCase
) : ViewModel() {

    val displayedItems = MutableLiveData<List<SurahListItem>>()
    val lastRead = MutableLiveData<LastRead>()
    val isLastReadDisplayed = Transformations.map(lastRead) {
        it.lastReadSurahNumber != 0 || it.lastReadVerseNumber != 0
    }
    val lastReadDateDisplayedItem = Transformations.map(lastRead) {
        it.lastReadTimeStamp.toDate()
    }
    val lastReadVerseNumberDisplayedItem = Transformations.map(lastRead) {
        "Ayat ${it.lastReadVerseNumber}"
    }

    private var isInitialized: Boolean = false

    private val isFailedToLoadDataEvent = SingleLiveEvent<Boolean>()
    val isProgressBarDisplayed = SingleLiveEvent<Boolean>()
    val isErrorStateDisplayed = MediatorLiveData<Boolean>().apply {
        addSource(isFailedToLoadDataEvent) {
            value = getErrorState(
                isFailedToLoadData = it,
                isProgressBarDisplayed = isProgressBarDisplayed.value ?: false,
                isDisplayedItemsEmpty = displayedItems.value?.isNullOrEmpty() ?: false
            )
        }
        addSource(isProgressBarDisplayed) {
            value = getErrorState(
                isFailedToLoadData = isFailedToLoadDataEvent.value ?: false,
                isProgressBarDisplayed = it,
                isDisplayedItemsEmpty = displayedItems.value?.isNullOrEmpty() ?: false
            )
        }
        addSource(displayedItems) {
            value = getErrorState(
                isFailedToLoadData = isFailedToLoadDataEvent.value ?: false,
                isProgressBarDisplayed = isProgressBarDisplayed.value ?: false,
                isDisplayedItemsEmpty = it.isNullOrEmpty()
            )
        }
    }

    private fun getErrorState(
        isFailedToLoadData: Boolean,
        isProgressBarDisplayed: Boolean,
        isDisplayedItemsEmpty: Boolean
    ): Boolean {
        return isFailedToLoadData && !isProgressBarDisplayed && isDisplayedItemsEmpty
    }

    fun initialize() {
        if (isInitialized) return
        getSurahList()
        isInitialized = true
    }

    fun getLastReadData() {
        getLastReadUseCase.getLastRead()
            .onEach {
                lastRead.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getSurahList() {
        getSurahUseCase.getSurah()
            .onStart {
                isProgressBarDisplayed.setValue(true)
                isFailedToLoadDataEvent.setValue(false)
            }
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        displayedItems.value = getSurahDisplayedItems(result.data)
                    }
                    is Result.Failure -> {
                        displayedItems.value = emptyList()
                        isFailedToLoadDataEvent.setValue(true)
                    }
                    is Result.ApiError -> {
                        displayedItems.value = emptyList()
                        isFailedToLoadDataEvent.setValue(true)
                    }
                }
            }
            .catch {
                isFailedToLoadDataEvent.setValue(true)
            }
            .onCompletion {
                isProgressBarDisplayed.setValue(false)
            }
            .launchIn(viewModelScope)
    }

    private fun getSurahDisplayedItems(surah: List<Surah>): List<SurahListItem> {
        return surah.map {
            SurahListItem.Value(
                number = it.surahNumber.toString(),
                nameInArab = it.surahNameInArab,
                name = it.surahName,
                revelation = it.surahRevelation,
                verseNumber = "${it.surahVerseNumber} ayat"
            )
        }
    }
}