package com.common.reglib.exception

class CommonException : RuntimeException {
    constructor(detailMessage: String) : super(detailMessage)
    constructor(detailMessage: String, throwable: Throwable) : super(detailMessage, throwable)
}