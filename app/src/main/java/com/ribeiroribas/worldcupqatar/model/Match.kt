package com.ribeiroribas.worldcupqatar.model

import com.ribeiroribas.worldcupqatar.util.Constants.DEFEAT
import com.ribeiroribas.worldcupqatar.util.Constants.DRAW
import com.ribeiroribas.worldcupqatar.util.Constants.VICTORY
import java.time.LocalDate
import java.time.LocalTime

data class Match(
    val team1: String ="",
    val scoreTeam1: Int? = null,
    val team2: String = "",
    val scoreTeam2: Int? = null,
) {
    val matchTime: LocalTime? = null
    var groupTeam1: String? = null
    var groupTeam2: String? = null
    val date: LocalDate? = null

    fun getMatchResult(teamName: String): String? {

        var resultTeam1 = DRAW
        var resultTeam2 = DRAW

        scoreTeam1?.let { score1 ->
            scoreTeam2?.let { score2 ->
                if (score1 > score2) {
                    resultTeam1 = VICTORY
                    resultTeam2 = DEFEAT
                } else if (score1 < score2) {
                    resultTeam1 = DEFEAT
                    resultTeam2 = VICTORY
                }

                when (teamName) {
                    team1 -> return resultTeam1
                    else -> return resultTeam2
                }
            }
        }

        return null
    }

    fun getGs(teamName: String): Int? {
        scoreTeam1?.let { score1 ->
            scoreTeam2?.let { score2 ->
                if(team1 == teamName) return score1
                return score2
            }
        }
        return null
    }

    fun getGc(teamName: String): Int? {
        scoreTeam1?.let { score1 ->
            scoreTeam2?.let { score2 ->
                if(team1 == teamName) return score2
                return score1
            }
        }
        return null
    }

}
