package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.datasources.remote.MovieService
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.CategoryRepositoryImpl
import rs.raf.vezbe11.data.repositories.MovieRepository
import rs.raf.vezbe11.data.repositories.MovieRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.CategoryViewModel

val categoryModule = module {
    viewModel { CategoryViewModel(categoryRepository = get()) }

    single<CategoryRepository> { CategoryRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getCategoryDao() }

    single<CategoryService> { create(retrofit = get()) }

}