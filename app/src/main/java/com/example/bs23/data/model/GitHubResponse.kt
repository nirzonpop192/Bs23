package com.example.bs23.model

data class GitHubResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)