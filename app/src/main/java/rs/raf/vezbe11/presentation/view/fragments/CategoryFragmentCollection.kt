package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.FragmentCollectionBinding
import rs.raf.vezbe11.databinding.FragmentContainerBinding
import rs.raf.vezbe11.databinding.FragmentFilterContainerBinding

class CategoryFragmentCollection(
    private val remoteContainer: PlanRemoteContainer?
): Fragment(R.layout.fragment_collection) {
    private var _binding: FragmentCollectionBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCollectionBinding.bind(view)

        if(remoteContainer==null) {
            val initialFragment =
                RecycleFragment(null, this, null) // Replace with your child Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.cont, initialFragment)
                .addToBackStack(null)
                .commit()
        }else{
            val initialFragment =
                RecycleFragment(null, null, remoteContainer) // Replace with your child Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.cont, initialFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    fun switchToDetailsFragment(mealId: Int){
        // Replace the container with the initial child Fragment
        val initialFragment = DetailMealFragment(mealId,null,this) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.cont, initialFragment)
            .addToBackStack(null)
            .commit()
    }
    fun switchToMealFragment(category:String)
    {
        val initialFragment = RecycleMealFragment(category, null,null,null,this, null, null,null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.cont, initialFragment)
            .addToBackStack(null)
            .commit()
    }
    fun switchWithSaveFragment(detail: Detail, detailMealFragment: DetailMealFragment)
    {
        val initialFragment = SaveFragment(detail, null, this) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.cont, initialFragment)
           // .remove(detailMealFragment)
            .addToBackStack(null)
            .commit()
    }
    fun switchWithSaveRecycle()
    {
        val initialFragment = RecycleSaveFragment(null,this,null, null, null) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.cont, initialFragment)
            .addToBackStack(null)
            .commit()
    }
    fun switchWithEditFragment(saveEntity: SaveEntity)
    {
        val initialFragment = EditFragment(saveEntity,null, this) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.cont, initialFragment)
            .addToBackStack(null)
            .commit()
    }
}