package ru.bellintegrator.util;

public class Response<D> {
    private D view;

    public Response(D view) {
        this.view = view;
    }

    public String sendResult() {
        return "{\"result\":" +
                view.toString() +
                "}";
    }

    public String sendError() {
        return "{\"error\":" +
                view.toString() +
                "}";
    }

    public String sendData() {
        return "{\"data\":" +
                view.toString() +
                "}";
    }

}
