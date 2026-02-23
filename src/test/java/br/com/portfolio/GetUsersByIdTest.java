package br.com.portfolio;

import br.com.portfolio.helpers.HelperFixture;
import br.com.portfolio.specs.ApiValidationSpecs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetUsersByIdTest extends BaseApiTest {
    private static String userId;

    @BeforeAll
    static void loadUserId() {
        userId = HelperFixture.getUserId("consulta");
    }

    @Test
    @DisplayName("Buscar usuário por Id")
    void searchUserById() {
        apiClient.getUserById(token, userId).then().spec(ApiValidationSpecs.validateGetUserById(userId));
    }

    @Test
    @DisplayName("usuário nao encontrado")
    void searchUserByIdNotExist() {
        apiClient.getUserById(token, "MhtZO7LfkiD2v24K").then().spec(ApiValidationSpecs.validateGetUserByIdNotFound());
    }

    @Test
    @DisplayName("Passar id com mais de 16 caracteres")
    void searchUserByIdBigSize() {
        apiClient.getUserById(token, "MhtZO7LfkiD2v24KsR").then().spec(ApiValidationSpecs.validateGetSizeId());
    }

    @Test
    @DisplayName("Passar id com menos de 16 caracteres")
    void searchUserByIdSmallerSize() {
        apiClient.getUserById(token, "MhtZO7Lf").then().spec(ApiValidationSpecs.validateGetSizeId());
    }
}

