package com.ribeiroribas.worldcupqatar.dao

import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupRanking

interface FilesDao {

    suspend fun getMatchesQualifier(): List<Match>

    suspend fun getTeamsRanking(): List<TeamRanking>

    suspend fun getWorldCupFinals(): List<WorldCupRanking>

}