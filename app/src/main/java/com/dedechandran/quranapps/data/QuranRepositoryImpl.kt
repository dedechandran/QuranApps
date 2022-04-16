package com.dedechandran.quranapps.data

import com.dedechandran.quranapps.common.network.NetworkResponse
import com.dedechandran.quranapps.common.Result
import com.dedechandran.quranapps.data.local.LocalDataSource
import com.dedechandran.quranapps.data.remote.RemoteDataSource
import com.dedechandran.quranapps.domain.LastRead
import com.dedechandran.quranapps.domain.QuranRepository
import com.dedechandran.quranapps.domain.Surah
import com.dedechandran.quranapps.domain.SurahDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.IllegalArgumentException
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : QuranRepository {
    override fun getSurah(): Flow<Result<List<Surah>>> {
        return flow {
            when (val response = remoteDataSource.getSurah()) {
                is NetworkResponse.Success -> {
                    val data =
                        response.body.data ?: throw IllegalArgumentException("Data cannot be null")
                    emit(Result.Success(data.toSurahModel()))
                }
                is NetworkResponse.ApiError -> {
                    emit(Result.ApiError(code = response.code, message = response.body.message))
                }
                is NetworkResponse.NetworkError -> {
                    emit(Result.Failure("Network Error"))
                }
                is NetworkResponse.UnknownError -> {
                    emit(Result.Failure("Unknown Error"))
                }
            }
        }
    }

    override fun getSurahDetails(surahNumber: Int): Flow<Result<SurahDetail>> {
        return flow {
            when (val response = remoteDataSource.getSurahDetails(surahNumber)) {
                is NetworkResponse.Success -> {
                    val data =
                        response.body.data ?: throw IllegalArgumentException("Data cannot be null")
                    emit(Result.Success(data.toSurahDetailModel()))
                }
                is NetworkResponse.ApiError -> {
                    emit(Result.ApiError(code = response.code, message = response.body.message))
                }
                is NetworkResponse.NetworkError -> {
                    emit(Result.Failure("Network Error"))
                }
                is NetworkResponse.UnknownError -> {
                    emit(Result.Failure("Unknown Error"))
                }
            }
        }
    }

    override fun getLastRead(): Flow<LastRead> {
        return flow {
            val lastReadSurahNumber = localDataSource.getLastReadSurahNumber()
            val lastReadVerseNumber = localDataSource.getLastReadVerseNumber()
            val lastReadSurahName = localDataSource.getLastReadSurahName()
            val lastReadTimestamp = localDataSource.getLastReadTimeStamp()
            val lastReadSurahNameInArab = localDataSource.getLastReadSurahNameInArab()
            val lastRead = LastRead(
                lastReadSurahNumber = lastReadSurahNumber,
                lastReadVerseNumber = lastReadVerseNumber,
                lastReadSurahName = lastReadSurahName,
                lastReadTimeStamp = lastReadTimestamp,
                lastReadSurahNameInArab = lastReadSurahNameInArab
            )
            emit(lastRead)
        }
    }

    override fun setLastRead(lastRead: LastRead): Flow<Unit> {
        return flow {
            emit(
                localDataSource.setLastRead(
                    timestamp = lastRead.lastReadTimeStamp,
                    surahNumber = lastRead.lastReadSurahNumber,
                    surahName = lastRead.lastReadSurahName,
                    verseNumber = lastRead.lastReadVerseNumber,
                    surahNameInArab = lastRead.lastReadSurahNameInArab
                )
            )
        }
    }
}