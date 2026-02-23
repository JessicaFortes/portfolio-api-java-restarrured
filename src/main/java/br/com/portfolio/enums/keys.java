package br.com.portfolio.enums;

public enum keys {
    BASE_URL("api.baseUrl");

    private final String key;

    keys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

