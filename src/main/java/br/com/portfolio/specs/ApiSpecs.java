package br.com.portfolio.specs;

import br.com.portfolio.config.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static br.com.portfolio.enums.keys.BASE_URL;

public class ApiSpecs {
    private static final PropertiesManager propertiesManager = PropertiesManager.getInstance();

    public static RequestSpecification getRequestBuilderSpec() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type", "application/json")
                .setBaseUri(propertiesManager.getProperty(BASE_URL))
                .build();
    }

    public static RequestSpecification getRequestBuilderAuthenticationSpec(String token) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", token))
                .setBaseUri(propertiesManager.getProperty(BASE_URL))
                .build();
    }
}

