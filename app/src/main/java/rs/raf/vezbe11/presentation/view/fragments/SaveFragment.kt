package rs.raf.vezbe11.presentation.view.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.databinding.FragmentSaveBinding
import rs.raf.vezbe11.presentation.contract.SaveContract
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.view.states.SaveState
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel
import timber.log.Timber
import java.util.Calendar


class SaveFragment(
    private val mealDetail: Detail,
    private val filterContainer: FragmentFilterContainer?,
    private val categoryFragmentCollection: CategoryFragmentCollection?
) : Fragment(R.layout.fragment_save) {
    private var _binding: FragmentSaveBinding? = null

    private val saveViewModel: SaveContract.ViewModel by sharedViewModel<SaveViewModel>()

    private val binding get() = _binding!!


    private var datePickerDialog: DatePickerDialog? = null
    private lateinit var dateButton: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        dateButton=binding.datePickerButton
        return binding.root
    }

    var selectedOptionText :String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initDatePicker()

        binding.mealTitle.text = mealDetail.strMeal
        Glide.with(context!!)
            .load(mealDetail.strMealThumb)
            .into(binding.mealImage);

        //pokretanje datepickera
        binding.datePickerButton.setOnClickListener({
            datePickerDialog!!.show()
        })

        binding.datePickerButton.setText(getTodaysDate())

        binding.radioGroupMealTypes.setOnCheckedChangeListener({_, checkedId ->
            var selected = binding.root.findViewById<RadioButton>(checkedId)
            selectedOptionText = selected.text.toString()
        })
        //save
        binding.btnSaveS.setOnClickListener({
            saveViewModel.save(mealDetail.toSaveEntity(selectedOptionText!!,binding.datePickerButton.text!!.toString()))
            Timber.e("USO U BUTTON SAVE")
        })


    }

    private fun initObservers() {
        saveViewModel.addSaveState.observe(viewLifecycleOwner, Observer {
            renderState(it)
            Timber.e("USO U OBSERVE SAVE")
        })

    }



    private fun renderState(state: AddSaveState) {
        when(state) {
            is AddSaveState.Success->{
                Toast.makeText(context, "Meal saved", Toast.LENGTH_SHORT)
                    .show()
                Timber.e("USO U")

                if(filterContainer!=null && categoryFragmentCollection==null)
                {
                    filterContainer.switchWithSaveRecycle()
                    Timber.e("USO U FILTER SAVE")

                }
                if(filterContainer==null && categoryFragmentCollection!=null){
                    categoryFragmentCollection.switchWithSaveRecycle()
                    Timber.e("USO U CONTAINER SAVE")
                }
            }
            is AddSaveState.Error -> Toast.makeText(context, "Error while saving meal", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun getTodaysDate(): String? {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        var month: Int = cal.get(Calendar.MONTH)
        month = month + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = makeDateString(day, month, year)!!
                dateButton!!.text = date
            }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(requireActivity(), style, dateSetListener, year, month, day)
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return getMonthFormat(month) + " " + day + " " + year
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "JAN"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "APR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AUG"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DEC" else "JAN"

        //default should never happen
    }

}