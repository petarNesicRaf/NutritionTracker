package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.DetailMealFragmentBinding
import rs.raf.vezbe11.databinding.FragmentEditBinding
import rs.raf.vezbe11.presentation.contract.DetailContract
import rs.raf.vezbe11.presentation.contract.SaveContract
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.view.states.EditMealState
import rs.raf.vezbe11.presentation.view.states.SaveState
import rs.raf.vezbe11.presentation.viewmodel.DetailViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel
import timber.log.Timber

class EditFragment(
private val saveEntity: SaveEntity,
private val filterContainer: FragmentFilterContainer?,
private val categoryContainer: CategoryFragmentCollection?
) : Fragment(R.layout.detail_meal_fragment) {
    private var _binding: FragmentEditBinding? = null

    private val saveViewModel: SaveContract.ViewModel by sharedViewModel<SaveViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.textViewMealName.text = saveEntity.strMeal

        Glide.with(context!!)
            .load(saveEntity.strMealThumb)
            .into(binding.mealImage);

        binding.buttonEd.setOnClickListener({
            var newMealName:String = binding.editTextMealName.text.toString()
            var newMealCategory:String = binding.editTextCategory.text.toString()
            var newMealArea:String = binding.editTextMealArea.text.toString()
            var newMealThumb:String = binding.editTextThumb.text.toString()
            var newMealYoutube:String = binding.editTextYoutube.text.toString()
            var newMealType:String = binding.editTextType.text.toString()
            var newDate:String = binding.editTextMealDate.text.toString()

            saveViewModel.update(saveEntity.idMeal,newMealName, newMealCategory,newMealArea,newMealThumb, newMealYoutube, newMealType, newDate)
        })
    }


    private fun initObservers() {
        saveViewModel.editState.observe(viewLifecycleOwner, Observer {
            renderState(it)
            Timber.e("USO U OBSERVE SAVE")
        })

    }


    private fun renderState(state: EditMealState) {
        when(state) {
            is EditMealState.Success->{
                Toast.makeText(context, "Meal saved", Toast.LENGTH_SHORT)
                    .show()

                if(filterContainer!=null && categoryContainer==null)
                {
                    filterContainer.switchWithSaveRecycle()

                }
                if(filterContainer==null && categoryContainer!=null){
                    categoryContainer.switchWithSaveRecycle()
                }
            }
            is EditMealState.Error -> Toast.makeText(context, "Error while saving meal", Toast.LENGTH_SHORT)
                .show()
        }
    }

}