package xyz.miyayu.attendancereader.model

import kotlinx.serialization.Serializable

@Serializable
data class Classifications(
    val id: Int,
    val schoolId: Int,
    val name: String,
    val isDecision: Boolean,
    val isClassExclusionDecision: Boolean
)
