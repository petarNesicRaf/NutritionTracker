package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.FragmentFilterContainerBinding

class FragmentFilterContainer: Fragment(R.layout.fragment_filter_container){


    private var _binding: FragmentFilterContainerBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFilterContainerBinding.bind(view)

        binding.btnCategory.setOnClickListener() {
            childFragmentManager.beginTransaction()
                .replace(R.id.containerFrameLayout, RecycleFragment(this,null,null))
                .addToBackStack(null)
                .commit()
        }


        binding.btnIngredients.setOnClickListener(){
            childFragmentManager.beginTransaction()
                .replace(R.id.containerFrameLayout, RecycleIngredientFragment(this))
                .addToBackStack(null)
                .commit()
        }

        binding.btnArea.setOnClickListener() {
            childFragmentManager.beginTransaction()
                .replace(R.id.containerFrameLayout, RecycleAreaFragment(this))
                .addToBackStack(null)
                .commit()
        }

        // Check if the child Fragment is already added
        if (childFragmentManager.findFragmentById(R.id.containerFrameLayout) == null) {
            // Replace the container with the initial child Fragment
            val initialFragment = RecycleAreaFragment(this) // Replace with your child Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.containerFrameLayout, initialFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    fun switchToSecondFragment(area:String){
            // Replace the container with the initial child Fragment
            val initialFragment = RecycleMealFragment(null, area,null, this,null,null,null,null) // Replace with your child Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.containerFrameLayout, initialFragment)
                .addToBackStack(null)
                .commit()
    }

    fun switchToIngredientFragment(ingredient:String){
        // Replace the container with the initial child Fragment
        val initialFragment = RecycleMealFragment(null, null, ingredient,this,null,null,null,null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
            .addToBackStack(null)
            .commit()
    }

    fun switchToDetailsFragment(mealId: Int){
        // Replace the container with the initial child Fragment
        val initialFragment = DetailMealFragment(mealId,this, null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
            .addToBackStack(null)
            .commit()
    }
    fun switchToCategoryFragment(categoryName: String){
        // Replace the container with the initial child Fragment
        val initialFragment = RecycleMealFragment(categoryName, null,null,this,null, null,null,null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
            .addToBackStack(null)
            .commit()
    }

    fun switchWithSaveFragment(mealDetails: Detail, detailFragment:DetailMealFragment)
    {
        val initialFragment = SaveFragment(mealDetails, this, null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
//            .remove(this)
            .addToBackStack(null)
            .commit()
    }

    fun switchWithSaveRecycle()
    {
        val initialFragment = RecycleSaveFragment(this,null,null,null, null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
//            .remove(saveFragment)
            .addToBackStack(null)
            .commit()
    }

    fun switchWithEditFragment(saveEntity: SaveEntity)
    {
        val initialFragment = EditFragment(saveEntity,this,null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, initialFragment)
            .addToBackStack(null)
            .commit()
    }
}