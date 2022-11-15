package com.ribeiroribas.worldcupqatar.network

import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.MatchesByDate
import java.time.LocalDate
import javax.inject.Inject

class WorldCupImpl @Inject constructor(
    private val api: WorldCupApi
) {
    suspend fun getMatchesByDate(date: LocalDate): MatchesByDate = api.getMatchesByDate(date)
    suspend fun getMatchesGroupStage(): List<Match> = api.getMatchesGroupStage()
    suspend fun getFinalMatch(): Match = api.getFinalMatch()
}