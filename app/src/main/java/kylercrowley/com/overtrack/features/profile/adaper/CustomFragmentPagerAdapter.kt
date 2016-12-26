package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class CustomFragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val fragments = LinkedHashMap<Fragment, String>()

    override fun getItem(position: Int): Fragment? {
        return fragments.keys.elementAt(position)
    }

    override fun getCount(): Int {
        return fragments.keys.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.put(fragment, title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragments.values.elementAt(position)
    }
}