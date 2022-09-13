package com.example.moviesnight.fragment

interface AppNavigator {
    fun navigateToDetail(movieID: Int)
    fun navigateToSearch()
    fun navigateToHome()
    fun navigateToSavedMovies()
}