package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.MealAreaService
import rs.raf.vezbe11.data.datasources.remote.MealIngredientService
import rs.raf.vezbe11.data.repositories.MealAreaRepoImpl
import rs.raf.vezbe11.data.repositories.MealAreaRepository
import rs.raf.vezbe11.data.repositories.MealIngredientRepoImpl
import rs.raf.vezbe11.data.repositories.MealIngredientRepository
import rs.raf.vezbe11.presentation.viewmodel.MealAreaViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealIngredientViewModel

val mealIngredient = module{
    viewModel { MealIngredientViewModel(mealIngredientRepo= get()) }

    single<MealIngredientRepository> { MealIngredientRepoImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getMealIngredientDao() }

    single<MealIngredientService> { create(retrofit = get()) }
}