package com.ribeiroribas.worldcupqatar.dao

import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupFinals

interface FilesDao {

    suspend fun getMatchesQualifier(): List<Match>

    suspend fun getRanking(): List<TeamRanking>

    suspend fun getWorldCupFinals(): List<WorldCupFinals>

}