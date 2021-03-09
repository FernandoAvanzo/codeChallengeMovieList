package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.toolbar.BuilderToolbarSettings.Builder

interface Navigation {

    fun buildToolbarSettings() = Builder.build()
}