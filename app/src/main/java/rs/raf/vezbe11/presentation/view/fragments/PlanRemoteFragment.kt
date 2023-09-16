package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentPlanLocalBinding
import rs.raf.vezbe11.databinding.FragmentPlanRemoteBinding

class PlanRemoteFragment (
    private val day:String
): Fragment(R.layout.fragment_plan_remote) {
    private var _binding: FragmentPlanRemoteBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlanRemoteBinding.bind(view)
        binding.tv.text = day

    }



}