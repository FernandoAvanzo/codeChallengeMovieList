package code.challenge.movieList.infrastructure.defaultComponents.model.entities

data class ComunicationProtocolModel(var titleId: Int?=0,
                                     var message: String?="",
                                     var isError: Boolean?=false,
                                     var load: Boolean? = true,
                                     var responseCode: Int?=0,
                                     var result: Any? = Any(),
                                     var request: Any? = Any())
