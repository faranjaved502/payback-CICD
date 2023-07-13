package com.hamza.payback.mvvm.usecase

interface UseCase<InputT, OutputT> {

    suspend fun execute(inputT: InputT): OutputT
}