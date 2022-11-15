package com.ribeiroribas.worldcupqatar.dao

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupFinals
import com.ribeiroribas.worldcupqatar.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FilesDaoImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
) : FilesDao {

    override suspend fun getMatchesQualifier(): List<Match> {
        val jsonData = appContext.resources.openRawResource(
            appContext.resources.getIdentifier(
                Constants.MATCHES_QUALIFIER_TXT,
                Constants.TYPE_RAW,
                appContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val mapper = ObjectMapper()
        return mapper.readValue(jsonData, Array<Match>::class.java).toList()
    }

    override suspend fun getRanking(): MutableList<TeamRanking> {

        val jsonData = appContext.resources.openRawResource(
            appContext.resources.getIdentifier(
                Constants.RANKING_TXT,
                Constants.TYPE_RAW,
                appContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val mapper = ObjectMapper()

        return mapper.readValue(jsonData, Array<TeamRanking>::class.java).toMutableList()
    }

    override suspend fun getWorldCupFinals(): MutableList<WorldCupFinals> {

        val jsonData = appContext.resources.openRawResource(
            appContext.resources.getIdentifier(
                Constants.WORLD_CUP_FINALS_TXT,
                Constants.TYPE_RAW,
                appContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val mapper = ObjectMapper()
        return mapper.readValue(jsonData, Array<WorldCupFinals>::class.java).toMutableList()

    }
}