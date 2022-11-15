package com.ribeiroribas.worldcupqatar.dao

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupRanking
import com.ribeiroribas.worldcupqatar.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FilesDaoImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val mapper: ObjectMapper
) : FilesDao {

    override suspend fun getMatchesQualifier(): List<Match> {
        val jsonData = Constants.MATCHES_QUALIFIER_TXT.readText()
        return mapper.readValue(jsonData, Array<Match>::class.java).toList()
    }

    override suspend fun getTeamsRanking(): MutableList<TeamRanking> {
        val jsonData = Constants.RANKING_TXT.readText()
        return mapper.readValue(jsonData, Array<TeamRanking>::class.java).toMutableList()
    }

    override suspend fun getWorldCupFinals(): MutableList<WorldCupRanking> {
        val jsonData = Constants.WORLD_CUP_FINALS_TXT.readText()
        return mapper.readValue(jsonData, Array<WorldCupRanking>::class.java).toMutableList()
    }

    private fun String.readText(): String = appContext.resources.openRawResource(
        appContext.resources.getIdentifier(
            this,
            Constants.TYPE_RAW,
            appContext.packageName
        )
    ).bufferedReader().use { it.readText() }

}