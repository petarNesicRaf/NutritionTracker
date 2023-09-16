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
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.databinding.FragmentCollectionBinding
import rs.raf.vezbe11.databinding.ListCategoriesFragmentBinding
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.viewmodel.CategoryViewModel
import timber.log.Timber

class RecycleFragment(
    private val fragContainer:FragmentFilterContainer?,
    private val catContainer:CategoryFragmentCollection?,
    private val remoteContainer: PlanRemoteContainer?
) : Fragment(R.layout.list_categories_fragment){
    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val categoryViewModel: CategoryContract.ViewModel by sharedViewModel<CategoryViewModel>()

    private var _binding: ListCategoriesFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryAdapter

    lateinit var mycontext:Context

    private var categoriesSave: List<CategoryEntity>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mycontext = container!!.context
        _binding = ListCategoriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        categoryViewModel.categoryState.map {
            Toast.makeText(context,"gggg " +it.toString(), Toast.LENGTH_LONG)
        }
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
        binding.listRvCategory.layoutManager = LinearLayoutManager(context)
        if(fragContainer == null && catContainer!=null && remoteContainer==null)
        {
            adapter = CategoryAdapter(requireContext(),requireActivity().supportFragmentManager,null ,catContainer!!,null)
            binding.listRvCategory.adapter = adapter
        }

        if(fragContainer!=null && catContainer==null && remoteContainer==null){
            adapter = CategoryAdapter(requireContext(), requireActivity().supportFragmentManager, fragContainer!!,null, null)
            binding.listRvCategory.adapter =adapter
        }

        if(fragContainer==null && catContainer==null && remoteContainer!=null){
            adapter = CategoryAdapter(requireContext(), requireActivity().supportFragmentManager, null,null, remoteContainer)
            binding.listRvCategory.adapter =adapter
        }

    }



    private fun initObservers() {
        categoryViewModel.categoryState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        categoryViewModel.getAllCategories()
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        categoryViewModel.fetchAll()
    }

    private fun renderState(state: CategoryState) {
        when (state) {
            is CategoryState.Success -> {
                showLoadingState(false)
                categoriesSave = state.categories

                adapter.submitList(state.categories)
            }
            is CategoryState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoryState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is CategoryState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputEt.isVisible = !loading
        binding.listRvCategory.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}