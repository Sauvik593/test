package com.albaco.depositorigination.common.exception;

public interface DepositOriginationErrorCodes {

    public static final int BAD_REQUEST = 400;
    public static final String BAD_REQUEST_MSG = "Bad Request";

    public static final int NOT_FOUND = 404;
    public static final String NOT_FOUND_MSG = "Not found";

    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String INTERNAL_SERVER_ERROR_MSG = "Oops! Looks like something went wrong at our end. Sorry for the inconvenience. Please try after sometime.";

    public static final int ACCESS_DENIED_ERROR = 403;
    public static final String ACCESS_DENIED_ERROR_MSG = "Access Denied";
}
