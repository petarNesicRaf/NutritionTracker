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
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.databinding.FragmentAreaCollectionBinding
import rs.raf.vezbe11.presentation.contract.AreaContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.AreaAdapter
import rs.raf.vezbe11.presentation.view.states.AreaState
import rs.raf.vezbe11.presentation.viewmodel.AreaViewModel
import timber.log.Timber

class RecycleAreaFragment(
    private val fragmentContainer:FragmentFilterContainer
): Fragment(R.layout.fragment_collection) {
    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val areaViewModel: AreaContract.ViewModel by sharedViewModel<AreaViewModel>()

    private var _binding: FragmentAreaCollectionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: AreaAdapter

    lateinit var mycontext: Context

    private var areaSave: List<AreaEntity>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mycontext = container!!.context
        _binding = FragmentAreaCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        areaViewModel.areaState.map {
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
        binding.listRvArea.layoutManager = LinearLayoutManager(context)
        //child
        //var childFragment = childFragmentManager.findFragmentByTag("FragmentFilterContainer")?.childFragmentManager
        adapter = AreaAdapter(requireContext(), requireActivity().supportFragmentManager, fragmentContainer)
        binding.listRvArea.adapter = adapter
    }



    private fun initObservers() {
        areaViewModel.areaState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        areaViewModel.getAllAreas()
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        areaViewModel.fetchAll()
    }

    private fun renderState(state: AreaState) {
        when (state) {
            is AreaState.Success -> {
                showLoadingState(false)
                areaSave = state.areas
                adapter.submitList(state.areas)
            }
            is AreaState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is AreaState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is AreaState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputEt.isVisible = !loading
        binding.listRvArea.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}