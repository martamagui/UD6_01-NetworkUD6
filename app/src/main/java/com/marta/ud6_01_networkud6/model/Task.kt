package com.marta.ud6_01_networkud6.model

data class Task(
    val description: String,
    val listIdFk: Int,
    val state: String,
    val taskId: Int,
    val title: String
)