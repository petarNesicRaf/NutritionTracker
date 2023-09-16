package rs.raf.vezbe11.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.vezbe11.modules.areaModule
import rs.raf.vezbe11.modules.categoryModule
import rs.raf.vezbe11.modules.movieModule
import rs.raf.vezbe11.modules.coreModule
import rs.raf.vezbe11.modules.detailModule
import rs.raf.vezbe11.modules.ingredientModule
import rs.raf.vezbe11.modules.mealAreaModule
import rs.raf.vezbe11.modules.mealIngredient
import rs.raf.vezbe11.modules.mealModule
import rs.raf.vezbe11.modules.planModule
import rs.raf.vezbe11.modules.saveModule
import rs.raf.vezbe11.modules.userModule
import timber.log.Timber

class Vezbe11Application : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            movieModule,
            userModule,
            categoryModule,
            mealModule,
            areaModule,
            mealAreaModule,
            ingredientModule,
            mealIngredient,
            detailModule,
            saveModule,
            planModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@Vezbe11Application)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}