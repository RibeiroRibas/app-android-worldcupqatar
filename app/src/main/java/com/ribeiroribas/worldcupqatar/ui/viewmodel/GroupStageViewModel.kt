package com.ribeiroribas.worldcupqatar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamScore
import com.ribeiroribas.worldcupqatar.repository.WorldCupQatarRepository
import com.ribeiroribas.worldcupqatar.ui.extensions.convertToTableValues
import com.ribeiroribas.worldcupqatar.ui.extensions.sortByTeamScore
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.GroupStageUiState
import com.ribeiroribas.worldcupqatar.ui.state.TableValues
import com.ribeiroribas.worldcupqatar.util.filterByTeamName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.TreeMap
import javax.inject.Inject

@HiltViewModel
class GroupStageViewModel @Inject constructor(
    private val repository: WorldCupQatarRepository
) : ViewModel() {

    private val _matchesGroupStageUiState =
        MutableStateFlow<AppUiState<List<GroupStageUiState>>>(
            AppUiState.Empty
        )
    val matchesGroupStageUiState: StateFlow<AppUiState<List<GroupStageUiState>>> =
        _matchesGroupStageUiState.asStateFlow()

    init {
        getMatchesGroupStage()
    }

    private fun getMatchesGroupStage() = viewModelScope.launch {
        _matchesGroupStageUiState.value = AppUiState.Loading
        repository.getMatchesGroupStage()
            .catch { error ->
                _matchesGroupStageUiState.value = AppUiState.Error(error.message.toString())
            }
            .collect { matches ->

                val mapMatchesByGroup: Map<String, List<Match>> = TreeMap(matches
                    .sortedBy { it.date }
                    .groupBy { it.groupTeam1 }
                )

                val groupStageUiState: MutableList<GroupStageUiState> = mutableListOf()
                mapMatchesByGroup.forEach { (group, matches) ->
                    groupStageUiState.add(
                        GroupStageUiState(
                            group = group,
                            matches = matches,
                            tableValuesList = matches.convertToTableValues()
                        )
                    )
                }

                _matchesGroupStageUiState.value = AppUiState.Loaded(groupStageUiState)
            }
    }

    private fun List<Match>.convertToTableValues(): List<List<TableValues>> {

        val teamScoreMutableList: MutableList<TeamScore> = mutableListOf()

        val teamNames = this.map { it.team1 }.toMutableList()
        teamNames.addAll(this.map { it.team2 })

        teamNames.distinct().forEach { teamName ->
            val teamScore = TeamScore(
                teamName = teamName,
                matches = filterByTeamName(
                    matches = this,
                    teamName = teamName
                )
            )
            teamScoreMutableList.add(teamScore)
        }

        return teamScoreMutableList
            .sortByTeamScore()
            .convertToTableValues()

    }

}


