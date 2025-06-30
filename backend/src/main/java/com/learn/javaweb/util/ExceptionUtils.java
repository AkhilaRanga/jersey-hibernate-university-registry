package com.learn.javaweb.util;

//import com.learn.webapp.invoker.ApiException;

public class ExceptionUtils {
    private ExceptionUtils() {}

    public static ApiWebException toApiException(Exception e, int statusCode) {
        return new ApiWebException(statusCode, e.getMessage());
    }
}
