package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.MealAreaService
import rs.raf.vezbe11.data.datasources.remote.MealService
import rs.raf.vezbe11.data.repositories.MealAreaRepoImpl
import rs.raf.vezbe11.data.repositories.MealAreaRepository
import rs.raf.vezbe11.data.repositories.MealRepository
import rs.raf.vezbe11.data.repositories.MealRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.MealAreaViewModel
import rs.raf.vezbe11.presentation.viewmodel.MealViewModel

val mealAreaModule = module{
    viewModel { MealAreaViewModel(mealAreaRepository = get()) }

    single<MealAreaRepository> { MealAreaRepoImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getMealAreaDao() }

    single<MealAreaService> { create(retrofit = get()) }
}