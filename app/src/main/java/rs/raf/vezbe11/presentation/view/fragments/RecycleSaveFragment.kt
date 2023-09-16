package rs.raf.vezbe11.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentCollectionMealBinding
import rs.raf.vezbe11.databinding.FragmentSaveRecycleBinding
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import rs.raf.vezbe11.presentation.contract.MealContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.SaveAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.SavePlanAdapter
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.view.states.SaveState
import rs.raf.vezbe11.presentation.viewmodel.MealAreaViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealIngredientViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealViewModel
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel
import timber.log.Timber

class RecycleSaveFragment(
private val containerFragment:FragmentFilterContainer?,
private val catContainerFragment: CategoryFragmentCollection?,
private val planLocal:PlanLocalContainer?,
    private val day:String?,
    private val planId:String?,
): Fragment(R.layout.fragment_save_recycle), IOnBackPressed{
    private val saveViewModel: SaveViewModel by sharedViewModel<SaveViewModel>()
    private val planViewMode: PlanViewModel by sharedViewModel<PlanViewModel>()

    private var _binding: FragmentSaveRecycleBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: SaveAdapter
    private lateinit var adapterPlan: SavePlanAdapter

    lateinit var mycontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mycontext = container!!.context
        _binding = FragmentSaveRecycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        //initListeners()
    }

    private fun initRecycler() {
        binding.listRvSave.layoutManager = LinearLayoutManager(context)
        if(containerFragment == null && catContainerFragment!=null)
        {
            adapter = SaveAdapter(requireContext(), null,catContainerFragment!!, saveViewModel)
            binding.listRvSave.adapter = adapter
        }else if(containerFragment!=null && catContainerFragment==null){
            adapter = SaveAdapter(requireContext(),  containerFragment!!, null, saveViewModel)
            binding.listRvSave.adapter =adapter
        }else{
            Timber.e("borba")
            //todo

            adapterPlan = SavePlanAdapter(requireContext(), planLocal!!, planViewMode, viewLifecycleOwner,day!!, planId!!, this)
            binding.listRvSave.adapter =adapterPlan
        }
    }



    private fun initObservers() {
            saveViewModel.saveState.observe(viewLifecycleOwner, Observer{
                Timber.e(it.toString())
                renderState(it)
            })

            saveViewModel.getAllSaved()
    }

    private fun renderState(state: SaveState) {
        when (state) {
            is SaveState.Success -> {
                showLoadingState(false)
                if(planLocal!=null) {
                    adapterPlan.submitList(state.save)
                }else {
                    adapter.submitList(state.save)
                }
            }
            is SaveState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SaveState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the db", Toast.LENGTH_LONG).show()
            }
            is SaveState.Loading -> {
                showLoadingState(true)
            }
        }
    }
    fun goBackToPlanFragment(planId:String)
    {
        val parent = parentFragment as PlanLocalContainer
        parent.switchToPlanFragment(planId)
    }




    private fun showLoadingState(loading: Boolean) {
        //binding.inputEt.isVisible = !loading
        binding.listRvSave.isVisible = !loading
        // binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        Timber.e("lalak")
        return true
    }

}