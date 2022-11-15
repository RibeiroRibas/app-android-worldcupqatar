package com.ribeiroribas.worldcupqatar.repository

import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.MatchesByDate
import com.ribeiroribas.worldcupqatar.network.WorldCupImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate
import javax.inject.Inject

class WorldCupQatarRepository @Inject constructor(
    private val webClient: WorldCupImpl
) {

    suspend fun getMatchesByDate(
        date: LocalDate
    ): Flow<MatchesByDate> = flow {
        emit(webClient.getMatchesByDate(date))
    }.flowOn(Dispatchers.IO)

    suspend fun getMatchesGroupStage(): Flow<List<Match>> = flow {
        emit(webClient.getMatchesGroupStage())
    }.flowOn(Dispatchers.IO)

    suspend fun getFinalMatch(): Flow<Match> = flow {
        emit(webClient.getFinalMatch())
    }.flowOn(Dispatchers.IO)
}

