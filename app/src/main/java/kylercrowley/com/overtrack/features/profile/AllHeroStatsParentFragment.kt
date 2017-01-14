package kylercrowley.com.overtrack.features.profile


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kylercrowley.com.overtrack.MODES
import kylercrowley.com.overtrack.PROFILE_ARRAY_KEY
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.features.profile.adaper.CustomFragmentPagerAdapter
import org.jetbrains.anko.find
import java.util.*

class AllHeroStatsParentFragment : Fragment() {

    private lateinit var mParams: Array<String>

    lateinit var tabLayout: TabLayout
        private set

    lateinit var viewPager: ViewPager
        private set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_all_hero_stats_parent, container, false)

        // Fragment may have been restored from an earlier state.
        if (savedInstanceState != null) {
            mParams = savedInstanceState.getStringArray(PROFILE_ARRAY_KEY)
        } else {
            mParams = arguments.getStringArray(PROFILE_ARRAY_KEY)
        }

        if (view != null) {
            setupTabLayout(view)
            return view
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArray(PROFILE_ARRAY_KEY, mParams)
    }

    private fun setupTabLayout(view: View) {
        tabLayout = view.find<TabLayout>(R.id.mode_tab_layout)
        setupViewPager(view, mParams)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(view: View, params: Array<String>) {
        viewPager = view.find<ViewPager>(R.id.mode_view_pager)

        val playerProfilePagerAdapter = CustomFragmentPagerAdapter(childFragmentManager)

        // Create two new Fragments. One for quickplay, one for competitive.
        // The mode must also be passed as a String to the fragment in a Bundle.
        val quickplayFragment = AllHeroStatsFragment()
        quickplayFragment.arguments = getBundle(true, params)

        val competitiveFragment = AllHeroStatsFragment()
        competitiveFragment.arguments = getBundle(false, params)

        playerProfilePagerAdapter.addFragment(quickplayFragment, resources.getString(R.string.quickplay))
        playerProfilePagerAdapter.addFragment(competitiveFragment, resources.getString(R.string.competitive))
        viewPager.adapter = playerProfilePagerAdapter
        viewPager.pageMargin = 0
    }

    private fun getBundle(isQuickplay: Boolean, params: Array<String>): Bundle {
        val bundle = Bundle()

        val newParams: ArrayList<String> = arrayListOf()
        newParams.addAll(params)
        if (isQuickplay) newParams.add(MODES[0]) else newParams.add(MODES[1])

        bundle.putStringArray(PROFILE_ARRAY_KEY, newParams.toTypedArray())

        return bundle
    }
}
