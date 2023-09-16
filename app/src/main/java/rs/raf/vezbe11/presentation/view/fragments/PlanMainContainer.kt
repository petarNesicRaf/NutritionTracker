package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentMPlanBinding

class PlanMainContainer(): Fragment(R.layout.fragment_m_plan) {

    private var _binding: FragmentMPlanBinding? = null

    val addPlan=AddPlanFragment()
  //  val planFragment=PlanFragment()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){

        //ovde stavi AddFragment
        //na action button stavi da se otvori addfragment
        childFragmentManager.beginTransaction()
            .replace(R.id.m_p, addPlan)
            .addToBackStack(null)
            .commitAllowingStateLoss()


    }

    fun switchPlanWithContainer(day:String, planId:String, itemView: View)
    {
        childFragmentManager.beginTransaction()
            .replace(R.id.m_p, PlanContainer(day,planId, itemView))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }





/*
    fun switchContainerWithPlan()
    {
        childFragmentManager.beginTransaction()
            .replace(R.id.m_p, planFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    fun switchToDays()
    {
        childFragmentManager.beginTransaction()
            .replace(R.id.m_p, planFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

 */
}