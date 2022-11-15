package com.ribeiroribas.worldcupqatar.ui.theme

import androidx.compose.ui.graphics.Color
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_A
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_B
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_C
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_D
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_E
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_F
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_G
import com.ribeiroribas.worldcupqatar.util.Constants.GROUP_H

val RedSecondaryMedium = Color(0xFF680021)
val RedSecondaryDark = Color(0xFF3b0000)

val GroupA = Color(0xFF4a0072)
val GroupB = Color(0xFF007c91)
val GroupC = Color(0xFFd32f2f)
val GroupD = Color(0xFFff8f00)
val GroupE = Color(0xFF6A0013)
val GroupF = Color(0xFF388e3c)
val GroupG = Color(0xFF0d47a1)
val GroupH = Color(0xFFec407a)

fun getColorByGroup(group: String?): Color {
    group?.let {
        if (group == GROUP_A) return GroupA
        if (group == GROUP_B) return GroupB
        if (group == GROUP_C) return GroupC
        if (group == GROUP_D) return GroupD
        if (group == GROUP_E) return GroupE
        if (group == GROUP_F) return GroupF
        if (group == GROUP_G) return GroupG
        if (group == GROUP_H) return GroupH
    }
    return RedSecondaryDark
}