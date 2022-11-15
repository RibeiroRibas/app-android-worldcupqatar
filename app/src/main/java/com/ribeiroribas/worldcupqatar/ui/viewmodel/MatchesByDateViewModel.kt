package com.ribeiroribas.worldcupqatar.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.MatchByDate
import com.ribeiroribas.worldcupqatar.model.TeamScore
import com.ribeiroribas.worldcupqatar.repository.WorldCupDataRepository
import com.ribeiroribas.worldcupqatar.repository.WorldCupQatarRepository
import com.ribeiroribas.worldcupqatar.ui.extensions.convertToTableValues
import com.ribeiroribas.worldcupqatar.ui.extensions.sortByTeamScore
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.MatchAndTeamsPerformance
import com.ribeiroribas.worldcupqatar.ui.state.MatchesByDateUiState
import com.ribeiroribas.worldcupqatar.ui.state.TeamPerformance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesByDateViewModel @Inject constructor(
    private val qatarRepository: WorldCupQatarRepository,
    private val worldCupDataRepository: WorldCupDataRepository
) : ViewModel() {

    private val _matchesByDateUiState =
        MutableStateFlow<AppUiState<MatchesByDateUiState>>(
            AppUiState.Empty
        )
    val matchesByDateUiState: StateFlow<AppUiState<MatchesByDateUiState>> =
        _matchesByDateUiState.asStateFlow()

    var month: String by mutableStateOf("")
        private set

    var selectedDate: LocalDate by mutableStateOf(LocalDate.now())
        private set


    init {
        val date = if (LocalDate.now().isBefore(LocalDate.of(2022, 11, 20))) {
            LocalDate.of(2022, 11, 20)
        } else {
            LocalDate.now()
        }
        getMatchesByDate(date)
        selectedDate = date
    }

    fun getMatchesByDate(date: LocalDate) = viewModelScope.launch {
        _matchesByDateUiState.value = AppUiState.Loading
        qatarRepository.getMatchesByDate(date)
            .catch { error ->
                _matchesByDateUiState.value = AppUiState.Error(error.message.toString())
            }
            .collect { matchesByDate ->

                val matchesByDateUiState = MatchesByDateUiState()
                matchesByDateUiState.stage = matchesByDate.stage

                matchesByDate.matches.forEach { matchByDate ->

                    val matchAndTeamsPerformance = MatchAndTeamsPerformance()

                    matchAndTeamsPerformance.match = matchByDate.match

                    getPerformanceInWorldCup(matchByDate) { teamPerformance ->
                        matchAndTeamsPerformance.teamsPerformance.add(teamPerformance)
                    }

                    getPerformanceInQualifier(matchByDate.match) { teamPerformance ->
                        matchAndTeamsPerformance.teamsPerformance.add(teamPerformance)
                    }

                    matchesByDateUiState.matchAndTeamsPerformance.add(matchAndTeamsPerformance)
                }

                _matchesByDateUiState.value = AppUiState.Loaded(matchesByDateUiState)
            }
    }

    fun updateMonth(month: String) {
        this.month = month
    }

    fun changeSelectedDate(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
    }

    private suspend fun getPerformanceInQualifier(
        match: Match,
        teamPerformance: (TeamPerformance) -> Unit
    ) {
        worldCupDataRepository.getMatchesQualifierByTeamName(
            teamNames = listOf(match.team1, match.team2)
        ).catch { error ->
            _matchesByDateUiState.value = AppUiState.Error(error.message.toString())
        }.collect { matchesByTeamName ->
            getTeamPerformance(mapMatchesByName = matchesByTeamName) { teamPerformance(it) }
        }
    }

    private fun getPerformanceInWorldCup(
        matchByDate: MatchByDate,
        teamPerformance: (TeamPerformance) -> Unit
    ) {
        val matchesByTeamName: MutableMap<String, List<Match>> = mutableMapOf()
        matchesByTeamName[matchByDate.match.team1] = matchByDate.matchesTeam1
        matchesByTeamName[matchByDate.match.team2] = matchByDate.matchesTeam2

        getTeamPerformance(mapMatchesByName = matchesByTeamName) { teamPerformance(it) }
    }

    private fun getTeamPerformance(
        mapMatchesByName: Map<String, List<Match>>,
        teamPerformance: (TeamPerformance) -> Unit
    ) {
        val teamPerformanceObj = TeamPerformance()
        val teamScoreMutableList: MutableList<TeamScore> = mutableListOf()

        mapMatchesByName.forEach { (teamName, matches) ->

            val teamScore = TeamScore(
                teamName = teamName,
                matches = matches
            )
            teamScoreMutableList.add(teamScore)

            teamPerformanceObj.teamsMatches.add(
                matches.sortedBy { it.date }.drop(1).toMutableList()
            )

        }

        teamPerformanceObj.tableValues = teamScoreMutableList
            .sortByTeamScore()
            .convertToTableValues()

        teamPerformance(teamPerformanceObj)
    }

}
