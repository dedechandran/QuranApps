package com.dedechandran.quranapps.ui.details

import android.util.Log
import androidx.lifecycle.*
import com.dedechandran.quranapps.common.Result
import com.dedechandran.quranapps.common.SingleLiveEvent
import com.dedechandran.quranapps.component.verse.VerseListItem
import com.dedechandran.quranapps.domain.GetSurahDetailUseCase
import com.dedechandran.quranapps.domain.LastRead
import com.dedechandran.quranapps.domain.SetLastReadUseCase
import com.dedechandran.quranapps.domain.SurahDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranDetailsViewModel @Inject constructor(
    private val getSurahDetailUseCase: GetSurahDetailUseCase,
    private val setLastReadUseCase: SetLastReadUseCase
) :
    ViewModel() {

    private val _surahDetails = MutableLiveData<SurahDetail>()
    val surahDetails: LiveData<SurahDetail> = Transformations.map(_surahDetails, this::getSurahDetailDisplayedItems)
    val verses = Transformations.map(_surahDetails, this::getVerseDisplayedItems)
    val onSuccessAddLastReadVerseEvent = SingleLiveEvent<String>()

    private val isFailedToLoadDataEvent = SingleLiveEvent<Boolean>()
    val isProgressBarDisplayed = SingleLiveEvent<Boolean>()
    val isErrorStateDisplayed = MediatorLiveData<Boolean>().apply {
        addSource(isFailedToLoadDataEvent) {
            value = getErrorState(
                isFailedToLoadData = it,
                isProgressBarDisplayed = isProgressBarDisplayed.value ?: false,
            )
        }
        addSource(isProgressBarDisplayed) {
            value = getErrorState(
                isFailedToLoadData = isFailedToLoadDataEvent.value ?: false,
                isProgressBarDisplayed = it,
            )
        }
    }

    private fun getErrorState(
        isFailedToLoadData: Boolean,
        isProgressBarDisplayed: Boolean
    ): Boolean {
        return isFailedToLoadData && !isProgressBarDisplayed
    }

    fun initialize(surahNumber: Int) {
        getSurahDetails(surahNumber)
    }

    private fun getSurahDetails(surahNumber: Int) {
        getSurahDetailUseCase.getSurahDetails(surahNumber)
            .onStart {
                isProgressBarDisplayed.setValue(true)
                isFailedToLoadDataEvent.setValue(false)
            }
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _surahDetails.value = result.data
                    }
                    is Result.ApiError -> {
                        isFailedToLoadDataEvent.setValue(true)
                    }
                    is Result.Failure -> {
                        isFailedToLoadDataEvent.setValue(true)
                    }
                }
            }
            .onCompletion {
                isProgressBarDisplayed.setValue(false)
            }
            .launchIn(viewModelScope)
    }

    private fun getVerseDisplayedItems(surahDetail: SurahDetail): List<VerseListItem> {
        val verseDisplayedItems = mutableListOf<VerseListItem>()
        if (surahDetail.surahPreBismillah != null) {
            verseDisplayedItems.add(
                VerseListItem.Bismillah(value = surahDetail.surahPreBismillah.arab)
            )
        }

        surahDetail.surahVerses.forEach {
            verseDisplayedItems.add(
                VerseListItem.Value(
                    number = it.verseNumber,
                    verseInArab = it.verseInArab,
                    verseInEnglish = it.verseInEnglish,
                    translationId = it.verseTranslationId,
                    translationEn = it.verseTranslationEn
                )
            )
        }
        return verseDisplayedItems
    }

    private fun getSurahDetailDisplayedItems(surahDetail: SurahDetail): SurahDetail {
        return surahDetail.copy(
            surahVerseNumber = "${surahDetail.surahVerseNumber} Ayat"
        )
    }

    fun onSetLastReadClicked(verseNumber: Int, timeStamp: Long) {
        viewModelScope.launch {
            val lastRead = LastRead(
                lastReadSurahNumber = _surahDetails.value?.surahNumber ?: 0,
                lastReadVerseNumber = verseNumber,
                lastReadTimeStamp = timeStamp,
                lastReadSurahName = _surahDetails.value?.surahName.orEmpty(),
                lastReadSurahNameInArab = _surahDetails.value?.surahNameInArab.orEmpty()
            )
            setLastReadUseCase.setLastRead(lastRead)
                .onStart { }
                .onEach {
                    onSuccessAddLastReadVerseEvent.setValue("Berhasil dijadikan ayat yang terakhir di baca")
                }
                .onCompletion { }
                .launchIn(this)
        }
    }

}