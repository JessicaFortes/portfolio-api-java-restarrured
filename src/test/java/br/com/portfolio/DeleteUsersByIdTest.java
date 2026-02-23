package br.com.portfolio;

import br.com.portfolio.helpers.HelperFixture;
import br.com.portfolio.specs.ApiValidationSpecs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteUsersByIdTest extends BaseApiTest {
    private static String userId;

    @BeforeAll
    static void loadUserId() {
        userId = HelperFixture.getUserId("remover");
    }

    @Test
    @DisplayName("Remover usuário por Id")
    void deleteUsersById() throws Exception {
        apiClient.deleteUser(token, userId)
                .then()
                .spec(ApiValidationSpecs.validateDeleteUser());
    }

    @Test
    @DisplayName("Remover passando id do usuário inexistente")
    void deleteUserNotExist() throws Exception {
            apiClient.deleteUser(token, "qbMqntef4iTOwWfg")
                    .then()
                    .spec(ApiValidationSpecs.validateDeleteUserNotExist());
        }

    @Test
    @DisplayName("Remover usuário que possui carrinho cadastrado")
    void errorDeleteUserWithCart() throws Exception {
        apiClient.deleteUser(token, "0uxuPY0cbmQhpEz1")
                .then()
                .spec(ApiValidationSpecs.validateDeleteUserWithCart());
    }


}

