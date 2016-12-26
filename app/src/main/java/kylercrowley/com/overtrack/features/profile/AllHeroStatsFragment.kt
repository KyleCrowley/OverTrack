package kylercrowley.com.overtrack.features.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kylercrowley.com.overtrack.App

import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.di.player_profile.DaggerPlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityModule

class AllHeroStatsFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_quickplay_all_stats, container, false)
    }

}
