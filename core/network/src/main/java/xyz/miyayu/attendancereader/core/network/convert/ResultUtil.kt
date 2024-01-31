package xyz.miyayu.attendancereader.core.network.convert

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import retrofit2.Response

fun <T, U> Response<T>.toResult(
    converter: (T) -> U
): Result<U, Throwable> {
    val body = this.body()
    return if (this.isSuccessful && body != null) {
        Ok(converter.invoke(body))
    } else {
        Err(HttpException(this))
    }
}

fun <T> Response<T>.toResult(
): Result<T, Throwable> {
    val body = this.body()
    return if (this.isSuccessful && body != null) {
        Ok(body)
    } else {
        Err(HttpException(this))
    }
}