package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities

import java.io.Serializable

data class ComunicationProtocolModel(
    var titleId: Int? = 0,
    var message: String? = "",
    var isError: Boolean? = false,
    var load: Boolean? = true,
    var responseCode: Int? = 0,
    var result: Any? = Any(),
    var request: Any? = Any()
) : Serializable
