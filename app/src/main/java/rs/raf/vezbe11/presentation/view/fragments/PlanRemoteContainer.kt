package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentLocalContainerBinding
import rs.raf.vezbe11.databinding.FragmentRemoteContainerBinding
import timber.log.Timber

class PlanRemoteContainer(private val day:String,
                          private val planId:String): Fragment(R.layout.fragment_remote_container) {

    private var _binding: FragmentRemoteContainerBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRemoteContainerBinding.bind(view)


        val initialFragment = CategoryFragmentCollection(this)
        childFragmentManager.beginTransaction()
            .replace(R.id.remote_container, initialFragment)
            .addToBackStack(null)
            .commit()

    }
    fun switchToMealRecycler(categoryName: String){
        // Replace the container with the initial child Fragment
        val initialFragment = RecycleMealFragment(categoryName, null,null,null,null, this, day, planId) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.remote_container, initialFragment)
            .addToBackStack(null)
            .commit()
    }

    fun switchToPlanFragment(){
        // Replace the container with the initial child Fragment
       // val initialFragment = PlanFragment(planId!!) // Replace with your child Fragment
       // parentFragment!!.childFragmentManager.beginTransaction()
       //     .replace(R.id.remote_container, initialFragment)
       //     .addToBackStack(null)
       //     .commit()

       childFragmentManager.beginTransaction()
            .replace(R.id.remote_container, PlanFragment(planId!!))
            .commitAllowingStateLoss()
    }
}