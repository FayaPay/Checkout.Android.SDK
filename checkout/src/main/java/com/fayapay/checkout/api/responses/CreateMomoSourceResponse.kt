package com.fayapay.checkout.api.responses

data class Owner(val verifiedPhone: String = "",
                 val phone: String = "",
                 val name: String? = null,
                 val verifiedName: String? = null,
                 val email: String? = null,
                 val verifiedEmail: String? = null)


data class CreateMomoSourceResponse(val owner: Owner,
                                    val maskedName: String = "",
                                    val amount: Int = 0,
                                    val metadata: Map<String, Any>,
                                    val livemode: Boolean = false,
                                    val statementDescriptor: String? = null,
                                    val type: String = "",
                                    val useType: String = "",
                                    val createdAt: String = "",
                                    val currency: String = "",
                                    val clientSecret: String = "",
                                    val id: String = "",
                                    val state: String = "",
                                    val flow: String = "",
                                    val customer: String? = null)
