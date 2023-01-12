package uz.gxteam.variantmarket.di.repositoryModel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepository
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModel {
    @Binds
    fun providesApiRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository
}