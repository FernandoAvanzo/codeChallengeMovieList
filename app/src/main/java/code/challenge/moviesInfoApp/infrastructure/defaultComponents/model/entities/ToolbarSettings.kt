package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ToolbarSettings(
    @SerializedName("showToolBar")
    val showToolBar: Boolean = false,
    @SerializedName("toolBarTitle")
    val toolBarTitle: String = "",
    @SerializedName("hasBackAction")
    val hasBackAction: Boolean = false,
    @SerializedName("screenBackAction")
    val screenBackAction: (() -> Any) = {},
    @SerializedName("hasCloseAction")
    val hasCloseAction: Boolean = false,
    @SerializedName("closeAction")
    val closeAction: (() -> Any) = {},
    @SerializedName("hasShareAction")
    val hasShareAction: Boolean = false,
    @SerializedName("shareAction")
    val shareAction: (() -> Any) = {},
    @SerializedName("hasHelpAction")
    val hasHelpAction: Boolean = false,
    @SerializedName("helpAction")
    val helpAction: (() -> Any) = {},
    @SerializedName("hasCloseRightAction")
    val hasCloseRightAction: Boolean = false,
    @SerializedName("closeRightAction")
    val closeRightAction: (() -> Any) = {}
) : Serializable
