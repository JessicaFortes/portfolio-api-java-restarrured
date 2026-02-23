package br.com.portfolio;

import br.com.portfolio.clients.ApiClient;
import br.com.portfolio.helpers.TokenProvider;
import br.com.portfolio.support.TestDataSeeder;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseApiTest {

    protected static final ApiClient apiClient = new ApiClient();
    protected static final TokenProvider tokenProvider = new TokenProvider(apiClient);
    protected static String token;
    private static final AtomicBoolean SEEDED = new AtomicBoolean(false);

    @BeforeAll
    static void baseSetup() {
        token = tokenProvider.getToken();
        if (SEEDED.compareAndSet(false, true)) {
            TestDataSeeder.seed(apiClient, token);
        }
    }
}

