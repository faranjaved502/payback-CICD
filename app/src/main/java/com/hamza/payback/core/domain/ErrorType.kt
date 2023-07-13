package com.hamza.payback.core.domain

sealed class ErrorType(val errorId : String) {

    /**
     * Generic Error exceptions.
     */
    object GENERIC : ErrorType("GENERIC-ERROR")

    /**
     * Error used when the API return a valid error
     */
    object HTTP : ErrorType("HTTP-ERROR")

    /**
     * Error used for conversion
     */
    object CONVERSION : ErrorType("CONVERSION-ERROR")

    /**
     * Network error, Probably timeout or others
     */
    object NETWORK : ErrorType("NETWORK-ERROR")

    /**
     * SSL Error if present
     */
    object SSL : ErrorType("SSL-ERROR")
}