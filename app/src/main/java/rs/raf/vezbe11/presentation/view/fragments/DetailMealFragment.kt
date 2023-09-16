package rs.raf.vezbe11.presentation.view.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.databinding.DetailMealFragmentBinding
import rs.raf.vezbe11.presentation.contract.DetailContract
import rs.raf.vezbe11.presentation.view.states.DetailState
import rs.raf.vezbe11.presentation.viewmodel.DetailViewModel
import timber.log.Timber
import java.util.regex.Pattern


class DetailMealFragment(
    private val mealId: Int,
    private val filterContainer: FragmentFilterContainer?,
    private val categoryContainer: CategoryFragmentCollection?
) : Fragment(R.layout.detail_meal_fragment){
    private var _binding: DetailMealFragmentBinding? = null

    private val detailViewModel: DetailContract.ViewModel by sharedViewModel<DetailViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailMealFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }


    private fun initObservers() {
        detailViewModel.detailState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        detailViewModel.fetch(mealId)

    }

    private fun renderState(state: DetailState) {
        when (state) {
            is DetailState.Success -> {
                showLoadingState(false)
                //categoriesSave = state.categories
                //adapter.submitList(state.categories)
                var detailMeal: Detail = state.details.meals.get(0)
                binding.mealTitle.text = "Meal title: "+  detailMeal.strMeal
                binding.youtubeLink.text = detailMeal.strYoutube

                binding.youtubeLink.setOnClickListener({
                    Timber.e("youtube"+getYouTubeId(detailMeal.strYoutube!!))
                    openYoutubeLink(getYouTubeId(detailMeal.strYoutube!!)!!)
                })
                binding.instructionsText.text = "Meal instruction: "+ detailMeal.strInstructions
                binding.mealArea.text ="Meal area: "+  detailMeal.strArea
                binding.mealCategory.text = "Meal category: "+ detailMeal.strCategory

                Glide.with(context!!)
                    .load(detailMeal.strMealThumb)
                    .into(binding.mealImage);

                binding.btnSave.setOnClickListener({
//                    if(filterContainer==null )
  //                      Timber.e("FC JE NULL")

    //                if(categoryContainer==null )
      //                  Timber.e("CC JE NULL")

                    if(filterContainer!=null && categoryContainer==null)
                    {
                        filterContainer.switchWithSaveFragment(detailMeal, this)
                        Timber.e("USO U PRVI DETAIL")
                    }else{
                        if(categoryContainer!=null){
                         categoryContainer!!.switchWithSaveFragment(detailMeal, this)
                         Timber.e("USO U DRUGI DETAIL")
                         }
                    }
                })
            }
            is DetailState.Error -> {
                showLoadingState(false)

                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is DetailState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is DetailState.Loading -> {
                showLoadingState(true)
            }
        }
    }


    private fun showLoadingState(loading: Boolean) {
        binding.mealImage.isVisible = !loading
        binding.mealTitle.isVisible = !loading
        binding.mealInstructions.isVisible = loading
    }
    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }
    }

    private fun getYouTubeId(youTubeUrl: String): String? {
        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(youTubeUrl)
        return if (matcher.find()) {
            matcher.group()
        } else {
            "error"
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}