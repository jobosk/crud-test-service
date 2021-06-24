package com.linkener.crudtest.controller.advice;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static String getMessage(final Exception e) {
        final Throwable cause = e.getCause();
        String message;
        if (cause instanceof JDBCException) {
            message = getMessage((JDBCException) cause);
        } else if (cause != null) {
            message = cause.getMessage();
        } else {
            message = null;
        }
        if (message == null) {
            message = e.getMessage();
        }
        if (message == null) {
            message = "Unknown exception";
        }
        return message;
    }

    private static String getMessage(final JDBCException e) {
        return e != null ? getMessage(e.getSQLException()) : "Unknown JDBC Exception";
    }

    private static String getMessage(final SQLException e) {
        return e != null ? e.getMessage() : "Unknown SQL Exception";
    }

    private static void alterResponse(final HttpServletResponse response, final int httpStatus, final String message) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            response.getWriter().append(message);
            response.setStatus(httpStatus);
        } catch (final IOException ioe) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(final Exception e, final HttpServletResponse response) {
        try {
            alterResponse(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), getMessage(e));
        } catch (final Exception exception) {
            alterResponse(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        }
    }
}