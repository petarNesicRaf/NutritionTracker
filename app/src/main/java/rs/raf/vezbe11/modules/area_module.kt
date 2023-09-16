package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.AreaService
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.repositories.AreaRepository
import rs.raf.vezbe11.data.repositories.AreaRepositoryImpl
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.CategoryRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.AreaViewModel
import rs.raf.vezbe11.presentation.viewmodel.CategoryViewModel

val areaModule = module{
    viewModel { AreaViewModel(areaRepository = get()) }

    single<AreaRepository> { AreaRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getAreaDao() }

    single<AreaService> { create(retrofit = get()) }

}