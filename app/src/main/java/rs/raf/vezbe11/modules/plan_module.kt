package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MovieDataBase
import rs.raf.vezbe11.data.repositories.PlanRepository
import rs.raf.vezbe11.data.repositories.PlanRepositoryImpl
import rs.raf.vezbe11.data.repositories.SaveRepository
import rs.raf.vezbe11.data.repositories.SaveRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel

val planModule = module {

    viewModel { PlanViewModel(planRepository = get()) }

    single<PlanRepository> { PlanRepositoryImpl(localDataSource = get()) }

    single { get<MovieDataBase>().getPlanDao() }


}