package com.evaluation.usecase.errors

import com.evaluation.data.error.Error
import com.evaluation.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by Rajesh
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
