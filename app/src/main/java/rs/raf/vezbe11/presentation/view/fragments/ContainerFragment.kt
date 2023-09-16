package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentContainerBinding
import rs.raf.vezbe11.presentation.view.adapters.CategoryPagerAdapter

class ContainerFragment : Fragment(R.layout.fragment_container){

    private var _binding: FragmentContainerBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
    }

    private fun initRecycler() {
        binding.viewPager.adapter =
            CategoryPagerAdapter(
                activity!!.supportFragmentManager,
                this.context, this
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*
    fun switchToMealsByCatFragment(categoryName:String)
    {
        val initialFragment = RecycleMealFragment(categoryName, null,null,null, this) // Replace with your child Fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.container, initialFragment)
            .commit()
    }

     */
}