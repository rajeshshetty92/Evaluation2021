package com.evaluation.usecase.errors

import com.evaluation.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
