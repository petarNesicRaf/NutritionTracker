package rs.raf.vezbe11.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.DateCount
import rs.raf.vezbe11.databinding.FragmentInputBinding
import rs.raf.vezbe11.databinding.FragmentStatisticsBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.contract.SaveContract
import rs.raf.vezbe11.presentation.view.states.DateCountState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel


class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val saveViewMode: SaveContract.ViewModel by sharedViewModel<SaveViewModel>()

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntry: ArrayList<BarEntry>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun initObservers() {
        saveViewMode.weeklyCount.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        saveViewMode.getWeeklyCount()
    }

    private fun init(){
        initObservers()
    }

    private fun getBarEntries(weeklyCount:List<DateCount>){
        barEntry = ArrayList()
        for(item in weeklyCount){
            barEntry.add(BarEntry(item.dateNumber.toFloat(),item.count.toFloat()))
        }
        barChart=binding.barChart
        barDataSet= BarDataSet(barEntry,"Number of meals added in the last week")
        barData=BarData(barDataSet)
        barChart.data=barData
        //barDataSet.color=resources.values
        barDataSet.valueTextColor= Color.BLACK
        barDataSet.valueTextSize=16f
        barChart.description.isEnabled=false
        barChart.notifyDataSetChanged()
        barChart.invalidate()
    }

    private fun renderState(state: DateCountState) {
        when(state) {
            is DateCountState.Success -> {
                getBarEntries(state.weeklyCount)
            }
            is DateCountState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }
}