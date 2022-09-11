package com.example.moviesnight

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.moviesnight.fragment.AppNavigator
import com.example.moviesnight.fragment.DetailFragment
import com.example.moviesnight.fragment.HomeFragment
import com.example.moviesnight.fragment.SearchFragment

class MainActivity : AppCompatActivity(),AppNavigator {
    private lateinit var homeButton: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.frameHomeContainer,HomeFragment())
//            .add(R.id.frameDetailContainer,DetailFragment())
//            .add(R.id.frameSearchContainer,SearchFragment())
//            .commit()
    }

    override fun navigateToDetail() {
        supportFragmentManager.beginTransaction().add(R.id.frameDetailContainer,DetailFragment()).commit()
    }

    override fun navigateToSearch() {
        supportFragmentManager.beginTransaction().add(R.id.frameSearchContainer,SearchFragment()).commit()
    }

    override fun navigateToHome() {
        supportFragmentManager.beginTransaction().add(R.id.frameHomeContainer,HomeFragment()).commit()
    }
}