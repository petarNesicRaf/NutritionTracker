package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.datasources.remote.MovieService
import rs.raf.vezbe11.data.repositories.MovieRepository
import rs.raf.vezbe11.data.repositories.MovieRepositoryImpl
import rs.raf.vezbe11.data.repositories.UserRepository
import rs.raf.vezbe11.data.repositories.UserRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel { UserViewModel(userRepository=get()) }

    single<UserRepository> { UserRepositoryImpl(localDataSource = get()) }

    single { get<MovieDataBase>().getUserDao() }

}