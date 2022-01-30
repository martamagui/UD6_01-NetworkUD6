package com.marta.ud6_01_networkud6.model

data class Task(
    val taskId: Int,
    val listIdFk: Int,
    val description: String,
    val state: String,
    val title: String
)