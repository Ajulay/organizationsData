package ru.bellintegrator.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Обработчик исходящих данных
 */
@ControllerAdvice(basePackages = "ru.bellintegrator.controllers")
public class JSONResponseWrapper implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * Обертка исходящих данных
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ControlerExceptionHandler.CustomException) {
            return body;
        }
        if (body == null) {
            return new VoidWrapper("success");
        }
        return new Wrapper(body);
    }

    private class Wrapper {
        private final Object data;

        public Wrapper(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

    }

    private class VoidWrapper {
        private final Object data;

        public VoidWrapper(Object data) {
            this.data = data;
        }

        public Object getResult() {
            return data;
        }

    }
}