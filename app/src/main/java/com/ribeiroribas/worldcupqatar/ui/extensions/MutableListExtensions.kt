package com.ribeiroribas.worldcupqatar.ui.extensions

import com.ribeiroribas.worldcupqatar.model.TeamScore
import com.ribeiroribas.worldcupqatar.ui.state.TableValues
import com.ribeiroribas.worldcupqatar.model.SortByTeamScore

fun List<TeamScore>.convertToTableValues(): List<List<TableValues>> {

    val tableValuesList: MutableList<List<TableValues>> = mutableListOf()
    val tableLines: MutableList<List<String>> = mutableListOf()
    val firstTableLine: List<String> = listOf(
        "", "", "P", "J", "V", "E", "D", "GP", "GC", "SG", "%",
    )
    tableLines.add(firstTableLine)
    this.forEach { teamScore ->
        tableLines.add(
            teamScore.convertToListOfString(
                position = this.indexOf(teamScore).plus(1).toString(),
            )
        )
    }
    tableLines.forEach { lineValues ->
        val tableValues: MutableList<TableValues> = mutableListOf()
        lineValues.forEachIndexed { index, value ->
            if (index == 1) {
                tableValues.add(
                    TableValues(
                        text = value,
                        cellWeight = 3f
                    )
                )
            } else {
                tableValues.add(
                    TableValues(
                        text = value,
                        cellWeight = 1f
                    )
                )
            }
        }
        tableValuesList.add(tableValues)
    }
    return tableValuesList
}

fun MutableList<TeamScore>.sortByTeamScore(): MutableList<TeamScore> {
    this.sortWith(SortByTeamScore())
    return this
}




