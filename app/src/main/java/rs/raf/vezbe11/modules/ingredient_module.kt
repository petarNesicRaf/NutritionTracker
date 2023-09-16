package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.datasources.remote.IngredientService
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.CategoryRepositoryImpl
import rs.raf.vezbe11.data.repositories.IngredientRepository
import rs.raf.vezbe11.data.repositories.IngredientRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.CategoryViewModel
import rs.raf.vezbe11.presentation.viewmodel.IngredientViewModel

val ingredientModule = module {
    viewModel { IngredientViewModel(ingredientRepository = get()) }

    single<IngredientRepository> { IngredientRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getIngredientDao() }

    single<IngredientService> { create(retrofit = get()) }

}