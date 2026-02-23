package br.com.portfolio.enums;

public enum Endpoints {
    ENDPOINT_USERS("/usuarios"),
    ENDPOINT_LOGIN("/login");

    private final String endpoints;

    Endpoints(String endpoints) {
        this.endpoints = endpoints;
    }

    public String getEndpoints() {
        return endpoints;
    }
}

