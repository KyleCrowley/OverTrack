package kylercrowley.com.overtrack.di

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.scopes.FragmentScope

@Module
abstract class FragmentModule(protected val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment = fragment
}