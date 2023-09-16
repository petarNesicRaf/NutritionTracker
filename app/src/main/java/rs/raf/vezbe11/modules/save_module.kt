package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.MovieService
import rs.raf.vezbe11.data.repositories.MovieRepository
import rs.raf.vezbe11.data.repositories.MovieRepositoryImpl
import rs.raf.vezbe11.data.repositories.SaveRepository
import rs.raf.vezbe11.data.repositories.SaveRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel


val saveModule = module {

    viewModel { SaveViewModel(saveRepository = get()) }

    single<SaveRepository> { SaveRepositoryImpl(localDataSource = get()) }

    single { get<MovieDataBase>().getSaveDeo() }


}