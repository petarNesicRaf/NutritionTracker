package rs.raf.vezbe11.presentation.view.adapters

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanFragment
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalFragment
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteFragment
import timber.log.Timber

class PlanPagerAdapter  (
    private val fragmentManager: FragmentManager,
    private val day:String,
    private val planId:String,
    private val itemv: View
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
    }


    override fun getItem(position: Int): Fragment {
        Timber.e("pager")

        return when(position) {
            FRAGMENT_1 -> PlanLocalContainer(day, planId,itemv)
            else-> PlanRemoteContainer(day,planId)
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "Saved"
            else -> "Remote"
        }
    }

}