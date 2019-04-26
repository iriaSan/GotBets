package com.iriasan.gotbets.core.exception


/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object Unauthorized : Failure()
    object ServerError : Failure()
    object SharedPreferencesError : Failure()
    object ListNotAvailable : Failure()
    object NoCase : Failure()
    data class EmptyField(val field: Any?) : Failure()
    data class Error(val field: Any?) : Failure()
    data class FailureWithType(val failure: Failure, val type: Any?) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
