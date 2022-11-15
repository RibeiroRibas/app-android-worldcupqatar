package com.ribeiroribas.worldcupqatar.model

import com.ribeiroribas.worldcupqatar.util.Constants.DEFEAT
import com.ribeiroribas.worldcupqatar.util.Constants.DRAW
import com.ribeiroribas.worldcupqatar.util.Constants.VICTORY

class TeamScore(
     var teamName: String = "",
     var points: Int = 0,
     var numberOfMatches: Int = 0,
     var victories: Int = 0,
     var defeats: Int = 0,
     var draws: Int = 0,
     var goalsScored: Int = 0,
     var goalsConceded: Int = 0,
     var goalsDifference: Int = 0,
     var percentage: Int = 0,
) {

    constructor(teamName: String,matches: List<Match>):this(){
        this.teamName = teamName
        matches.forEach { match ->
            setMatchResult(matchResult = match.getMatchResult(teamName))
            setGoalScored(score = match.getGs(teamName))
            setGoalConceded(conceded = match.getGc(teamName))
        }
        calculateGoalDifference()
        calculatePercentage()
    }

    private fun setMatchResult(matchResult: String?) {
        matchResult?.let { result ->
            numberOfMatches = numberOfMatches.plus(1)

            when (result) {
                VICTORY -> {
                    points = points.plus(3)
                    victories = victories.plus(1)
                }
                DRAW -> {
                    points = points.plus(1)
                    draws = draws.plus(1)
                }
                DEFEAT -> {
                    defeats = defeats.plus(1)
                }
            }
        }
    }

   private fun setGoalScored(score: Int?) {
        score?.let {
            this.goalsScored = this.goalsScored.plus(it)
        }
    }

    private fun setGoalConceded(conceded: Int?) {
        conceded?.let {
            this.goalsConceded = this.goalsConceded.plus(it)
        }
    }

    private fun calculateGoalDifference() {
        goalsDifference = goalsScored.minus(goalsConceded)
    }

    private fun calculatePercentage() {
        val playedPoints: Double = 3.0 * numberOfMatches
        val result: Double = (points.div(playedPoints)).times(100)
        this.percentage = result.toInt()
    }

    fun convertToListOfString(position: String): List<String> {
        return mutableListOf(
            position,
            teamName,
            points.toString(),
            numberOfMatches.toString(),
            victories.toString(),
            draws.toString(),
            defeats.toString(),
            goalsScored.toString(),
            goalsConceded.toString(),
            goalsDifference.toString(),
            percentage.toString(),
        )
    }

}
