package com.ribeiroribas.worldcupqatar.network

import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.MatchesByDate
import retrofit2.http.GET
import retrofit2.http.Path
import java.time.LocalDate
import javax.inject.Singleton

@Singleton
interface WorldCupApi {

    @GET("qatar/matches-by-date/{date}")
    suspend fun getMatchesByDate(@Path("date") date: LocalDate): MatchesByDate

    @GET("qatar/all-matches")
    suspend fun getMatchesGroupStage(): List<Match>

    @GET("qatar/final-match")
    suspend fun getFinalMatch(): Match
}