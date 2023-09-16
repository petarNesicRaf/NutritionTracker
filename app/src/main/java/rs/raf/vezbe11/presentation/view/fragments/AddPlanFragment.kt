package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.PlanEntity
import rs.raf.vezbe11.databinding.FragmentAddPlanBinding
import rs.raf.vezbe11.databinding.FragmentPlanBinding
import rs.raf.vezbe11.presentation.contract.PlanContract
import rs.raf.vezbe11.presentation.contract.SaveContract
import rs.raf.vezbe11.presentation.view.states.AddPlanState
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel
import timber.log.Timber

class AddPlanFragment: Fragment(R.layout.fragment_add_plan) {

    private val planViewModel: PlanContract.ViewModel by sharedViewModel<PlanViewModel>()

    private var _binding: FragmentAddPlanBinding? = null

    private val binding get() = _binding!!
    var strDate:String? = null
    var strName:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddPlanBinding.bind(view)
        initObservers()

        binding.btnSave.setOnClickListener({
             strDate = binding.editDate.text.toString()
             strName = binding.editTitle.text.toString()
            if(strDate!=null && strName != null)
            {
                planViewModel.insertPlan(PlanEntity(strPlanName = strName!!,weekStartDate = strDate, monday = null, tuesday = null, wednesday = null, thursday = null, friday = null, saturday = null, sunday = null))
            }
        })
    }
    private fun initObservers() {
        planViewModel.addPlanState.observe(viewLifecycleOwner, Observer {
            renderState(it)
            Timber.e("USO U OBSERVE SAVE")
        })

    }



    private fun renderState(state: AddPlanState) {
        when(state) {
            is AddPlanState.Success->{
                Toast.makeText(context, "Plan saved", Toast.LENGTH_SHORT)
                    .show()
                Timber.e("USO U")

                var parent = parentFragment!!.childFragmentManager
                    .beginTransaction()
                    .replace(R.id.m_p, PlanFragment(strName!!))
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
            is AddPlanState.Error -> Toast.makeText(context, "Error while saving plan", Toast.LENGTH_SHORT)
                .show()
        }
    }


}