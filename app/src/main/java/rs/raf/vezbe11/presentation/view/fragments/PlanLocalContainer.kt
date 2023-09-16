package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentFilterContainerBinding
import rs.raf.vezbe11.databinding.FragmentLocalContainerBinding
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import timber.log.Timber

class PlanLocalContainer(private val day:String,
    private val planId:String,
    private val itemv:View
): Fragment(R.layout.fragment_local_container) {

    private var _binding: FragmentLocalContainerBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLocalContainerBinding.bind(view)

        Timber.e("planlocalcontainer")


        val initialFragment = RecycleSaveFragment(null,null,this, day!!, planId) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
                .replace(R.id.local_container, initialFragment)
                .addToBackStack(null)
                .commit()

    }

    fun switchToPlanFragment(planId:String){
        //todo
        childFragmentManager.beginTransaction()
            .replace(R.id.local_container, PlanFragment(planId!!))
            .commitAllowingStateLoss()
    }

    fun getItemv():View
    {
        return itemv
    }
}