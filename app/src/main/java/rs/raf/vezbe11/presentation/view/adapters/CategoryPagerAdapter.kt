package rs.raf.vezbe11.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.InputFragment
import rs.raf.vezbe11.presentation.view.fragments.PlanFragment
import rs.raf.vezbe11.presentation.view.fragments.PlanMainContainer
import rs.raf.vezbe11.presentation.view.fragments.RecycleFragment
import rs.raf.vezbe11.presentation.view.fragments.StatisticsFragment

class CategoryPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val context: Context?,
    private val containerFragment: ContainerFragment
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 4
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
        const val FRAGMENT_4 = 4

    }


    override fun getItem(position: Int): Fragment {
        return when(position) {

            FRAGMENT_1 -> CategoryFragmentCollection(null)
            FRAGMENT_2 -> FragmentFilterContainer()
            FRAGMENT_3 -> PlanMainContainer()
            else -> StatisticsFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> context!!.getString(R.string.categories)
            FRAGMENT_2 -> "Filter"
            FRAGMENT_3 -> "Plan"
            else -> "STATS"
        }
    }

}