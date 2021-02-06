package com.evaluation.ui.base

import androidx.lifecycle.ViewModel
import com.evaluation.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by Rajesh
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
