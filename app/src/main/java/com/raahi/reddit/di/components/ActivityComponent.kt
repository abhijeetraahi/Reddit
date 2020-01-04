package com.raahi.reddit.di.components

import com.raahi.reddit.di.ActivityScope
import com.raahi.reddit.di.modules.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

}
