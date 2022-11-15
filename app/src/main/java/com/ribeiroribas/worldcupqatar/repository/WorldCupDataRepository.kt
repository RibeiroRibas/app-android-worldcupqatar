package com.ribeiroribas.worldcupqatar.repository

import com.ribeiroribas.worldcupqatar.dao.FilesDaoImpl
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupFinals
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
        teamName1: String,
        teamName2: String
    ): Flow<Map<String, MutableList<Match>>> = flow {
        val matchesQualifier = dao.getMatchesQualifier()

        val teamMatches: MutableMap<String, MutableList<Match>> = mutableMapOf()

        val teamMatches1: MutableList<Match> = filterByTeamName(
            matches = matchesQualifier,
            teamName = teamName1
        ).toMutableList()
        teamMatches1.forEach { match ->
            match.groupTeam1 = getGroupByTeamName(match.team1)
            match.groupTeam2 = getGroupByTeamName(match.team2)
        }
        teamMatches[teamName1] = teamMatches1

        val teamMatches2: MutableList<Match> = filterByTeamName(
            matches = matchesQualifier,
            teamName = teamName2
        ).toMutableList()
        teamMatches2.forEach { match ->
            match.groupTeam1 = getGroupByTeamName(match.team1)
            match.groupTeam2 = getGroupByTeamName(match.team2)
        }
        teamMatches[teamName2] = teamMatches2

        emit(teamMatches)
    }.flowOn(Dispatchers.IO)

    suspend fun getWorldCupRanking(): Flow<MutableList<TeamRanking>> = flow {
        emit(dao.getRanking())
    }.flowOn(Dispatchers.IO)

    fun getWorldCupFinals(): Flow<MutableList<WorldCupFinals>> = flow {
        emit(dao.getWorldCupFinals())
    }.flowOn(Dispatchers.IO)

    private fun getGroupByTeamName(teamName: String): String? {
        val teams: MutableMap<String, String> = HashMap()
        teams["Alemanha"] = "GRUPO E"
        teams["Arábia Saudita"] = "GRUPO C"
        teams["Argentina"] = "GRUPO C"
        teams["Austrália"] = "GRUPO D"
        teams["Bélgica"] = "GRUPO F"
        teams["Brasil"] = "GRUPO G"
        teams["Camarões"] = "GRUPO G"
        teams["Canadá"] = "GRUPO F"
        teams["Catar"] = "GRUPO A"
        teams["Coreia do Sul"] = "GRUPO H"
        teams["Costa Rica"] = "GRUPO E"
        teams["Croácia"] = "GRUPO F"
        teams["Dinamarca"] = "GRUPO D"
        teams["Equador"] = "GRUPO A"
        teams["Espanha"] = "GRUPO E"
        teams["EUA"] = "GRUPO B"
        teams["França"] = "GRUPO D"
        teams["Gana"] = "GRUPO H"
        teams["Países Baixos"] = "GRUPO A"
        teams["Inglaterra"] = "GRUPO B"
        teams["Irã"] = "GRUPO B"
        teams["Japão"] = "GRUPO E"
        teams["Marrocos"] = "GRUPO F"
        teams["México"] = "GRUPO C"
        teams["País de Gales"] = "GRUPO B"
        teams["Polônia"] = "GRUPO C"
        teams["Portugal"] = "GRUPO H"
        teams["Senegal"] = "GRUPO A"
        teams["Sérvia"] = "GRUPO G"
        teams["Suíça"] = "GRUPO G"
        teams["Tunísia"] = "GRUPO D"
        teams["Uruguai"] = "GRUPO H"
        teams.forEach { (team, group) ->
            if (teamName == team)
                return group
        }
        return null
    }
}