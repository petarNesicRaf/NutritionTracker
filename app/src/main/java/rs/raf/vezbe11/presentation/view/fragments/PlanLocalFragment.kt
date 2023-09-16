package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentPlanLocalBinding

class PlanLocalFragment(
    private val day:String
) : Fragment(R.layout.fragment_plan_local){

    private var _binding: FragmentPlanLocalBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlanLocalBinding.bind(view)
        binding.tv.text = day
        //switchWithSaveRecycle()
    }
/*
    fun switchWithSaveRecycle()
    {
        val initialFragment = RecycleSaveFragment(null,null, this) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.m_p, initialFragment)
//            .remove(saveFragment)
            .addToBackStack(null)
            .commit()
    }

 */

}