package ru.kirea.pressureandpulsefirestore.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.kirea.pressureandpulsefirestore.data.AppUser
import ru.kirea.pressureandpulsefirestore.windows.main.MainFragment
import ru.kirea.pressureandpulsefirestore.windows.main.MainService
import ru.kirea.pressureandpulsefirestore.windows.main.MainViewModel

object Modules {
    //модуль, содержимое которого должно быть во всем приложении
    val application = module {
        single<AppUser>(qualifier = named(Scopes.USER_UID)) {
            AppUser.generate(get())
        }
    }

    //модуль основного экрана
    val mainWindow = module {
        scope<MainFragment> {
            viewModel(qualifier = named(Scopes.MAIN_VIEW_MODEL)) {
                MainViewModel(get(qualifier = named(Scopes.MAIN_SERVICE)))
            }

            scoped<MainService>(qualifier = named(Scopes.MAIN_SERVICE)) {
                MainService(get(qualifier = named(Scopes.USER_UID)))
            }
        }
    }
}
