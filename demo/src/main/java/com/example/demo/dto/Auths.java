package com.example.demo.dto;

public class Auths {

    private String url;
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Auths{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
