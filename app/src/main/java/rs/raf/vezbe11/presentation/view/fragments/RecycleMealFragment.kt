package rs.raf.vezbe11.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentCollectionBinding
import rs.raf.vezbe11.databinding.FragmentCollectionMealBinding
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.contract.MealContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.RemoteMealAdapter
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MealAreaState
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.viewmodel.MealAreaViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealIngredientViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealViewModel
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import timber.log.Timber

class RecycleMealFragment(
    private val categoryName:String?,
    private val areaName:String?,
    private val ingrName:String?,
    private val containerFragment:FragmentFilterContainer?,
    private val catContainerFragment: CategoryFragmentCollection?,
    private val remoteContainer: PlanRemoteContainer?,
    private val day:String?,
    private val planId:String?
): Fragment(R.layout.fragment_collection_meal){
    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mealViewModel: MealContract.ViewModel by sharedViewModel<MealViewModel>()
    private val mealAreaViewModel:MealAreaViewModel  by sharedViewModel<MealAreaViewModel>()
    private val mealIngredientViewModel:MealIngredientViewModel by sharedViewModel<MealIngredientViewModel>()
    private val planViewMode: PlanViewModel by sharedViewModel<PlanViewModel>()

    private var _binding: FragmentCollectionMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MealAdapter
    private lateinit var remoteAdapter: RemoteMealAdapter

    lateinit var mycontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mycontext = container!!.context
        _binding = FragmentCollectionMealBinding.inflate(inflater, container, false)
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
        initListeners()
    }

    fun initListeners()
    {
        binding.inputEt.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initRecycler() {
        binding.listRvMeal.layoutManager = LinearLayoutManager(context)
        if(containerFragment == null && catContainerFragment!=null && remoteContainer==null)
        {
            adapter = MealAdapter(requireContext(), null,catContainerFragment!!)
            binding.listRvMeal.adapter = adapter
        }else if(containerFragment != null && catContainerFragment==null && remoteContainer==null){
            adapter = MealAdapter(requireContext(),  containerFragment!!, null)
            binding.listRvMeal.adapter =adapter
        }else{
            remoteAdapter = RemoteMealAdapter(requireContext(), remoteContainer!!, planViewMode, viewLifecycleOwner,day!!, planId!!, this)
            binding.listRvMeal.adapter =remoteAdapter

        }
    }



    private fun initObservers() {
        if(ingrName != null && areaName==null && categoryName==null) {
            mealIngredientViewModel.mealState.observe(viewLifecycleOwner, Observer{
                Timber.e(it.toString())
                renderState(it)
            })
            mealIngredientViewModel.getMealsByIngredient(ingrName)
            mealIngredientViewModel.fetchAll(ingrName)
        }else if(areaName != null && categoryName==null && ingrName == null)
        {

            mealAreaViewModel.mealAreaState.observe(viewLifecycleOwner, Observer{
                    Timber.e(it.toString())
                    renderState(it)
            })
            mealAreaViewModel.getMealsByArea(areaName)
            mealAreaViewModel.fetchAll(areaName)
        } else if(areaName == null && categoryName!=null && ingrName==null) {
            mealViewModel.mealState.observe(viewLifecycleOwner, Observer {
                Timber.e(it.toString())
                renderState(it)
            })
            mealViewModel.getMealsByCategory(categoryName)

            mealViewModel.fetchAll(categoryName)
        }
    }

    private fun renderState(state: MealState) {
        when (state) {
            is MealState.Success -> {
                showLoadingState(false)
                if(remoteContainer==null)
                {
                    adapter.submitList(state.meals)
                }else{
                    remoteAdapter.submitList(state.meals)
                }
            }
            is MealState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MealState.Loading -> {
                showLoadingState(true)
            }
        }
    }
    fun goBackToPlanFragment()
    {
        val parent = parentFragment as PlanRemoteContainer
        parent.switchToPlanFragment()
    }


    private fun showLoadingState(loading: Boolean) {
        //binding.inputEt.isVisible = !loading
        binding.listRvMeal.isVisible = !loading
       // binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}