package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.toolbar

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ToolbarSettings

sealed class BuilderToolbarSettings {

    companion object Builder {
        private var showToolBar = false
        private var toolBarTitle = ""
        private var hasBackAction = false
        private var screenBackAction: (() -> Any) = {}
        private var hasCloseAction = false
        private var closeAction: (() -> Any) = {}
        private var hasShareAction: Boolean = false
        private var shareAction: (() -> Any) = {}
        private var hasHelpAction: Boolean = false
        private var shareHelp: (() -> Any) = {}
        private var hasCloseRightAction: Boolean = false
        private var closeRightAction: (() -> Any) = {}

        fun showToolBar(show: Boolean) = apply { showToolBar = show }
        fun toolBarTitle(title: String) = apply { toolBarTitle = title }
        fun hasBackAction(hasAction: Boolean) = apply { hasBackAction = hasAction }
        fun screenBackAction(action: (() -> Any)) = apply { screenBackAction = action }
        fun hasCloseAction(hasAction: Boolean) = apply { hasCloseAction = hasAction }
        fun closeAction(action: (() -> Any)) = apply { closeAction = action }
        fun hasShareAction(hasAction: Boolean) = apply { hasShareAction = hasAction }
        fun shareAction(action: (() -> Any)) = apply { shareAction = action }
        fun hasHelpAction(hasAction: Boolean) = apply { hasHelpAction = hasAction }
        fun helpAction(action: (() -> Any)) = apply { shareHelp = action }
        fun hasCloseRightAction(hasAction: Boolean) = apply { hasCloseRightAction = hasAction }
        fun closeRightAction(action: (() -> Any)) = apply { closeRightAction = action }

        fun build() = ToolbarSettings(
            showToolBar,
            toolBarTitle,
            hasBackAction,
            screenBackAction,
            hasCloseAction,
            closeAction,
            hasShareAction,
            shareAction,
            hasHelpAction,
            shareHelp,
            hasCloseRightAction,
            closeRightAction).apply { resetValues() }

        private fun resetValues() {
            showToolBar = false
            toolBarTitle = ""
            hasBackAction = false
            screenBackAction = {}
            hasCloseAction = false
            closeAction = {}
            hasShareAction = false
            shareAction = {}
            hasHelpAction = false
            shareHelp = {}
            hasCloseRightAction = false
            closeRightAction = {}
        }
    }

}
