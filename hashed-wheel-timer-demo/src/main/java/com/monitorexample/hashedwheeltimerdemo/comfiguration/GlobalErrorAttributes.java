package com.monitorexample.hashedwheeltimerdemo.comfiguration;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorMap = super.getErrorAttributes(request, options);
        Throwable error = getError(request);
        if (error instanceof WebExchangeBindException) {
            errorMap.put("message", ((WebExchangeBindException)error).getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        } else if (error instanceof ResponseStatusException) {
            errorMap.put("message", ((ResponseStatusException)error).getReason());
        } else {
            errorMap.put("message", error.getMessage());
        }
        return errorMap;
    }
}
