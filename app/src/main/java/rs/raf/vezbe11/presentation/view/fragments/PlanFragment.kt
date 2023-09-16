package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.FragmentLoginBinding
import rs.raf.vezbe11.databinding.FragmentPlanBinding

class PlanFragment (
    private val planId:String?,
): Fragment(R.layout.fragment_plan) {

    private var _binding: FragmentPlanBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlanBinding.bind(view)

        binding.monday.dayTv.text ="Monday"
        binding.tuesday.dayTv.text ="Tuesday"
        binding.wednesday.dayTv.text ="Wednesday"
        binding.thursday.dayTv.text ="Thursday"
        binding.friday.dayTv.text ="Friday"
        binding.saturday.dayTv.text ="Saturday"
        binding.sunday.dayTv.text ="Sunday"

        initListeners()
    }
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_plan, container, false)
        return rootView
    }

    fun initListeners()
    {
        binding.monday.btnAdd.setOnClickListener({
            switchWithAdapter("monday", planId!!)
        })
        binding.tuesday.btnAdd.setOnClickListener({
            switchWithAdapter("tuesday", planId!!)

        })
        binding.wednesday.btnAdd.setOnClickListener({
            switchWithAdapter("wednesday", planId!!)

        })
        binding.thursday.btnAdd.setOnClickListener({
            switchWithAdapter("thursday", planId!!)
        })
        binding.friday.btnAdd.setOnClickListener({
            switchWithAdapter("friday", planId!!)

        })
        binding.saturday.btnAdd.setOnClickListener({
            switchWithAdapter("saturday", planId!!)

        })
        binding.sunday.btnAdd.setOnClickListener({
            switchWithAdapter("sunday", planId!!)

        })
    }

    fun switchWithAdapter(day: String, planIdd: String)
    {
        var parent = parentFragment as PlanMainContainer
        parent.switchPlanWithContainer(day, planIdd,rootView!!)
    }


}