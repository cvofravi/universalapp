package com.codingmould.restproject.dailystandup.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ErrorConfig implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status == null) status = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        if (message == null) message = (String) request.getAttribute("jakarta.servlet.error.message");
        if (message == null || message.isBlank()) message = HttpStatus.valueOf(status != null ? status : 500).getReasonPhrase();

        HttpStatus httpStatus = HttpStatus.resolve(status != null ? status : 500);
        if (httpStatus == null) httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(httpStatus).body(Map.of(
            "status", httpStatus.value(),
            "error", httpStatus.getReasonPhrase(),
            "message", message,
            "path", String.valueOf(request.getAttribute("javax.servlet.error.request_uri") != null
                ? request.getAttribute("javax.servlet.error.request_uri")
                : request.getRequestURI())
        ));
    }
}
