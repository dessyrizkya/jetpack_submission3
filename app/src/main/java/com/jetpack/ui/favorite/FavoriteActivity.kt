package com.jetpack.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.R
import com.jetpack.databinding.ActivityFavoriteBinding
import com.jetpack.ui.home.MainActivity
import com.jetpack.ui.home.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    companion object {
        private val TAB_ICONS = intArrayOf(R.drawable.ic_film, R.drawable.ic_tv)
    }

    private lateinit var binding : ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsFavPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)?.setIcon(TAB_ICONS[0])
        binding.tabs.getTabAt(1)?.setIcon(TAB_ICONS[1])
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}