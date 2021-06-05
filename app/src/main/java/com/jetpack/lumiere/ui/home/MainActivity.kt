package com.jetpack.lumiere.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.lumiere.databinding.ActivityMainBinding
import com.jetpack.lumiere.R
import com.jetpack.lumiere.ui.content.favorite.FavoriteMovieActivity


class MainActivity : AppCompatActivity() {
    companion object {
        private val TAB_ICONS = intArrayOf(R.drawable.ic_film, R.drawable.ic_tv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        Toast.makeText(this,"No Internet connection",Toast.LENGTH_LONG).show();
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager)
        activityMainBinding.tabs.getTabAt(0)?.setIcon(TAB_ICONS[0])
        activityMainBinding.tabs.getTabAt(1)?.setIcon(TAB_ICONS[1])

        supportActionBar?.elevation = 0f

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                val moveIntent = Intent(this@MainActivity, FavoriteMovieActivity::class.java)
                startActivity(moveIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}