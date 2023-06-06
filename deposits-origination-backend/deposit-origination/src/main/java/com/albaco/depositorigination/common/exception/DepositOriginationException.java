package com.albaco.depositorigination.common.exception;

public class DepositOriginationException extends Exception
{
    private static final long serialVersionUID = 147315800L;
    private int code;
    private String message;

    private Object data;

    public DepositOriginationException()
    {
    }

    public DepositOriginationException(String message)
    {
        this.message = message;
    }

    public DepositOriginationException(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public DepositOriginationException(int code, String message, Object data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static DepositOriginationException accessDenied()
    {
        return new DepositOriginationException(DepositOriginationErrorCodes.ACCESS_DENIED_ERROR, DepositOriginationErrorCodes.ACCESS_DENIED_ERROR_MSG);
    }


    public DepositOriginationException(Throwable e)
    {
        super(e);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
