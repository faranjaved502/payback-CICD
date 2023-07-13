package com.hamza.payback.core.network.mapper

interface DomainMapper <NetworkT, DomainT> {

    fun map(response: NetworkT) : DomainT

    fun getApiResponseType() : Class<NetworkT>
}