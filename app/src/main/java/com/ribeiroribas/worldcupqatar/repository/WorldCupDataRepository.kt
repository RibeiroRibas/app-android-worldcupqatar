package com.ribeiroribas.worldcupqatar.repository

import com.ribeiroribas.worldcupqatar.dao.FilesDaoImpl
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupRanking
import com.ribeiroribas.worldcupqatar.util.filterByTeamName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WorldCupDataRepository @Inject constructor(
    private val dao: FilesDaoImpl
) {

    suspend fun getMatchesQualifierByTeamName(
        teamNames: List<String>
    ): Flow<Map<String, MutableList<Match>>> = flow {

        val matchesQualifier = dao.getMatchesQualifier()

        val mapMatchesByTeamName: MutableMap<String, MutableList<Match>> = mutableMapOf()

        teamNames.forEach { teamName ->

            val teamMatches: MutableList<Match> = filterByTeamName(
                matches = matchesQualifier,
                teamName = teamName
            ).toMutableList()

            mapMatchesByTeamName[teamName] = teamMatches
        }

        emit(mapMatchesByTeamName)

    }.flowOn(Dispatchers.IO)

    suspend fun getWorldCupRanking(): Flow<MutableList<TeamRanking>> = flow {
        emit(dao.getTeamsRanking())
    }.flowOn(Dispatchers.IO)

    fun getWorldCupFinals(): Flow<MutableList<WorldCupRanking>> = flow {
        emit(dao.getWorldCupFinals())
    }.flowOn(Dispatchers.IO)

}