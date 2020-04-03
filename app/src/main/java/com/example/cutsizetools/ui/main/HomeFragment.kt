package com.example.cutsizetools.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cutsizespeed.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


const val EMPTY_TITLE = "empty_title"
val TAB_TITLES = mapOf( 0 to "speed", 1 to "time")

class TabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter (fragmentManager,lifecycle)
{

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        if (position == 1) {
            fragment = PageFragment2()
        } else {
            fragment = PageFragment1()
            fragment.arguments = Bundle().apply {
                putInt(EMPTY_TITLE, position + 1)
            }
        }
        return fragment
    }

    //   fun getPageTitle(position: Int): CharSequence =
    //       activity.resources.getString(TAB_TITLES[position])

    //  override fun getItemCount(): Int = TAB_TITLES.size

    companion object {
      //  private val TAB_TITLES = arrayOf(
      //      R.string.tab_text_1,
      //      R.string.tab_text_2
      //  )
    }
}

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

     @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity: AppCompatActivity = activity as AppCompatActivity
        val fragmentView =
            requireNotNull(view) { "View should not be null when calling onActivityCreated" }



       val toolbar: Toolbar = fragmentView.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)

        val tabLayout : TabLayout = fragmentView.findViewById(R.id.tabs)
        val speedTab: TabLayout.Tab = tabLayout.getTabAt(1)!!
        viewPager = fragmentView.findViewById(R.id.view_pager)
        viewPager.adapter = TabsAdapter(childFragmentManager, lifecycle)

        // connect the tabs and view pager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
             if (position == 0) {
                 //     tab.setIcon(R.drawable.ic_camera_alt_black_24dp)
             }
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
        tabLayout.selectTab(speedTab)

        // handle tint for the camera icon
        val colors = resources.getColorStateList(R.color.tab_icon, activity.theme)

        for (i in 0 until tabLayout.tabCount) {
            val tab: TabLayout.Tab = tabLayout.getTabAt(i)!!
            var icon = tab.icon
            if (icon != null) {
                icon = DrawableCompat.wrap(icon)
                DrawableCompat.setTintList(icon, colors)
            }
        }
    }

}