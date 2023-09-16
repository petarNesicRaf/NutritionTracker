package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentContainerBinding
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import rs.raf.vezbe11.databinding.PlanContainerBinding
import rs.raf.vezbe11.presentation.view.adapters.CategoryPagerAdapter
import rs.raf.vezbe11.presentation.view.adapters.PlanPagerAdapter

class PlanContainer(
    private val day:String,
    private val planId:String,
    private val itemView:View
): Fragment(R.layout.plan_container) {

    private var _binding: PlanContainerBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
    }

    private fun initRecycler() {
        var parent = parentFragment!!.childFragmentManager
        binding.viewPager.adapter = PlanPagerAdapter(parent, day, planId,itemView)

        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlanContainerBinding.inflate(inflater, container, false)
        return binding.root
    }


}