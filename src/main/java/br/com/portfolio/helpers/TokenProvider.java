package br.com.portfolio.helpers;

import br.com.portfolio.clients.ApiClient;
import io.restassured.response.Response;

public class TokenProvider {
    private static final ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();
    private static final ThreadLocal<String> REFRESH_TOKEN = new ThreadLocal<>();
    private final ApiClient apiClient;

    public TokenProvider(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String getToken() {
        return getAccessToken();
    }

    public String getAccessToken() {
        ensureTokens();
        return ACCESS_TOKEN.get();
    }

    public String getRefreshToken() {
        ensureTokens();
        return REFRESH_TOKEN.get();
    }

    private void ensureTokens() {
        String access = ACCESS_TOKEN.get();
        String refresh = REFRESH_TOKEN.get();
        if ((access == null || access.isBlank()) || (refresh == null || refresh.isBlank())) {
            Response response = apiClient.login("credenciais.json");
            access = response.jsonPath().getString("accessToken");
            if (access == null || access.isBlank()) {
                access = response.jsonPath().getString("token");
            }
            refresh = response.jsonPath().getString("refreshToken");
            ACCESS_TOKEN.set(access);
            REFRESH_TOKEN.set(refresh);
        }
    }

    public void clear() {
        ACCESS_TOKEN.remove();
        REFRESH_TOKEN.remove();
    }
}

