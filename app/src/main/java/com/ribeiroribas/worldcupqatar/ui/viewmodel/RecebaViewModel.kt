package com.ribeiroribas.worldcupqatar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.model.TeamRanking
import com.ribeiroribas.worldcupqatar.model.WorldCupFinals
import com.ribeiroribas.worldcupqatar.repository.WorldCupDataRepository
import com.ribeiroribas.worldcupqatar.repository.WorldCupQatarRepository
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.TableValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

@HiltViewModel
class RecebaViewModel @Inject constructor(
    private val qatarRepository: WorldCupQatarRepository,
    private val worldCupDataRepository: WorldCupDataRepository
) : ViewModel() {

    private val _finalMatchUiState = MutableStateFlow<AppUiState<Match>>(AppUiState.Empty)
    val finalMatchUiState: StateFlow<AppUiState<Match>> = _finalMatchUiState.asStateFlow()

    private val _tableRankingUiState =
        MutableStateFlow<AppUiState<List<List<TableValues>>>>(AppUiState.Empty)
    val tableRankingUiState: StateFlow<AppUiState<List<List<TableValues>>>> =
        _tableRankingUiState.asStateFlow()

    private val _tableWorldCupFinalsUiState =
        MutableStateFlow<AppUiState<List<List<TableValues>>>>(AppUiState.Empty)
    val tableWorldCupFinalsUiState: StateFlow<AppUiState<List<List<TableValues>>>> =
        _tableWorldCupFinalsUiState.asStateFlow()

    init {
        getFinalMatch()
        getWoldCupRanking()
        getWorldCupFinals()
    }

    private fun getFinalMatch() = viewModelScope.launch {
        _finalMatchUiState.value = AppUiState.Loading
        qatarRepository.getFinalMatch()
            .catch { error ->
                _finalMatchUiState.value = AppUiState.Error(error.message.toString())
            }.collect { finalMatch ->
                _finalMatchUiState.value = AppUiState.Loaded(finalMatch)
            }
    }

    private fun getWoldCupRanking() = viewModelScope.launch {
        _tableRankingUiState.value = AppUiState.Loading
        worldCupDataRepository.getWorldCupRanking()
            .catch { error ->
                _tableRankingUiState.value = AppUiState.Error(error.message.toString())
            }.collect { teamsRanking ->
                val tableLines: List<List<TableValues>> = teamsRanking.convertToRankingTable()
                _tableRankingUiState.value = AppUiState.Loaded(tableLines)
            }
    }

    private fun getWorldCupFinals() = viewModelScope.launch {
        _tableWorldCupFinalsUiState.value = AppUiState.Loading
        worldCupDataRepository.getWorldCupFinals()
            .catch { error ->
                _tableWorldCupFinalsUiState.value = AppUiState.Error(error.message.toString())
            }.collect { worldCupFinals ->
                val tableLines: List<List<TableValues>> =
                    worldCupFinals.convertToWorldCupFinalsTable()
                _tableWorldCupFinalsUiState.value = AppUiState.Loaded(tableLines)
            }
    }


    private fun MutableList<WorldCupFinals>.convertToWorldCupFinalsTable(): List<List<TableValues>> {

        val valuesList: MutableList<List<String>> = mutableListOf()
        this.forEach {
            val collect = Stream.of(
                it.year, it.champion, it.vice, it.third, it.fourth
            ).collect(Collectors.toList())
            valuesList.add(collect)
        }

        val tableLines: MutableList<List<TableValues>> = mutableListOf()

        valuesList.forEach { values ->
            val tableValues: MutableList<TableValues> = mutableListOf()
            values.forEach { value ->
                tableValues.add(
                    TableValues(
                        text = value,
                        cellWeight = 1f
                    )
                )
            }
            tableLines.add(tableValues)
        }
        return tableLines
    }

    private fun MutableList<TeamRanking>.convertToRankingTable(): List<List<TableValues>> {
        val tableLines: MutableList<List<TableValues>> = mutableListOf()
        this.add(0, TeamRanking())
        this.forEach { teamRanking ->
            val tableValuesList: MutableList<TableValues> = mutableListOf()

            var tableValues = TableValues(
                text = teamRanking.position,
                cellWeight = 1f
            )
            tableValuesList.add(tableValues)

            tableValues = TableValues(
                text = teamRanking.team,
                cellWeight = 2f
            )
            tableValuesList.add(tableValues)

            tableValues = TableValues(
                text = teamRanking.years,
                cellWeight = 6f
            )
            tableValuesList.add(tableValues)

            tableLines.add(tableValuesList)
        }
        return tableLines
    }
}
