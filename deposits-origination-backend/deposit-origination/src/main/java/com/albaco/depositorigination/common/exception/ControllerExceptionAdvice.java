package com.albaco.depositorigination.common.exception;

import com.albaco.depositorigination.common.exception.DepositOriginationErrorCodes;
import com.albaco.depositorigination.common.exception.DepositOriginationException;
import com.albaco.depositorigination.common.response.ApiResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

    @ExceptionHandler(DepositOriginationException.class)
    public ResponseEntity<ApiResponseInfo> depositOriginationErrorHandler(DepositOriginationException e)
    {
        log.error("ERROR. ErrorCode: " + e.getCode() + ", ErrorMessage: " + e.getMessage());

        ApiResponseInfo info = null;

        if(e.getData()==null)
        {
            info = new ApiResponseInfo(e.getCode(), e.getMessage());
        }
        else
        {
            info = new ApiResponseInfo(e.getCode(), e.getMessage(),e.getData());
        }

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseInfo> accessDeniedHandler(AccessDeniedException e)
    {
        ApiResponseInfo info = new ApiResponseInfo(DepositOriginationErrorCodes.ACCESS_DENIED_ERROR, DepositOriginationErrorCodes.ACCESS_DENIED_ERROR_MSG);
        return new ResponseEntity<>(info, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponseInfo> urlNotFoundHandler(NoHandlerFoundException e)
    {
        log.error("ERROR 404: " + e.getMessage());
        ApiResponseInfo info = new ApiResponseInfo(DepositOriginationErrorCodes.NOT_FOUND, DepositOriginationErrorCodes.NOT_FOUND_MSG);
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ApiResponseInfo> badRequestHandler(Exception e)
    {
        log.error("BAD REQUEST: ", e);
        ApiResponseInfo info = new ApiResponseInfo(DepositOriginationErrorCodes.BAD_REQUEST, DepositOriginationErrorCodes.BAD_REQUEST_MSG);
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseInfo> allExceptionHandler(Exception e)
    {
        if (e != null && e.getMessage() != null && e.getMessage().contains("Broken pipe"))
        {
            log.error("SERVER ERROR: " + e);   //do not show full stacktrace for broken pipe UD2IOS-844
        }
        else
        {
            log.error("SERVER ERROR: ", e);
        }

        ApiResponseInfo info = new ApiResponseInfo(DepositOriginationErrorCodes.INTERNAL_SERVER_ERROR, DepositOriginationErrorCodes.INTERNAL_SERVER_ERROR_MSG);
        return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
