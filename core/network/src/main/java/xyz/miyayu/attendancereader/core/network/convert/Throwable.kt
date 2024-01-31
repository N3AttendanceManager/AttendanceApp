package xyz.miyayu.attendancereader.core.network.convert

import retrofit2.Response

class HttpException(
    response: Response<out Any?>
) : Exception(
    "Error. StatusCode: ${response.code()}"
)