package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.DetailMealService
import rs.raf.vezbe11.data.datasources.remote.IngredientService
import rs.raf.vezbe11.data.repositories.DetailRepository
import rs.raf.vezbe11.data.repositories.DetailRepositoryImpl
import rs.raf.vezbe11.data.repositories.IngredientRepository
import rs.raf.vezbe11.data.repositories.IngredientRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.DetailViewModel
import rs.raf.vezbe11.presentation.viewmodel.IngredientViewModel

val detailModule = module {
    viewModel { DetailViewModel(detailRepository = get()) }

    single<DetailRepository> { DetailRepositoryImpl(remoteDataSource = get()) }

    //single { get<MovieDataBase>().getIngredientDao() }

    single<DetailMealService> { create(retrofit = get()) }

}