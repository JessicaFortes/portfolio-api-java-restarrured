package br.com.portfolio;

import br.com.portfolio.dtos.UserDto;
import br.com.portfolio.fixtures.UpdateUsersFixture;
import br.com.portfolio.helpers.HelperFixture;
import br.com.portfolio.specs.ApiValidationSpecs;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PutUsersByIdTest extends BaseApiTest {
    private static String userId;
    private static UpdateUsersFixture fixture;

    @BeforeAll
    static void loadUserId() {
        userId = HelperFixture.getUserId("alteracao");
        fixture = new UpdateUsersFixture();
    }

    @Test
    @DisplayName("Alterar apenas email")
    void updateOnlyEmail() throws Exception {
        apiClient.putUser(token, userId, toJson(fixture.updateOnlyEmail()))
                .then()
                .spec(ApiValidationSpecs.validateUpdateUsers());
    }

    @Test
    @DisplayName("Alterar apenas senha")
    void updateOnlyPassword() throws Exception {
        apiClient.putUser(token, userId, toJson(fixture.updateOnlyPassword()))
                .then()
                .spec(ApiValidationSpecs.validateUpdateUsers());
    }

    @Test
    @DisplayName("Alterar apenas nome")
    void updateOnlyNome() throws Exception {
        apiClient.putUser(token, userId, toJson(fixture.updateOnlyNome()))
                .then()
                .spec(ApiValidationSpecs.validateUpdateUsers());
    }

    @Test
    @DisplayName("Alterar apenas administrador")
    void updateOnlyAdministrador() throws Exception {
        apiClient.putUser(token, userId, toJson(fixture.updateOnlyAdministrador()))
                .then()
                .spec(ApiValidationSpecs.validateUpdateUsers());
    }

    @Test
    @DisplayName("Alterar todos os dados do usuário")
    void updateUsers() throws Exception {
        apiClient.putUser(token, userId, toJson(fixture.updateUsers()))
                .then()
                .spec(ApiValidationSpecs.validateUpdateUsers());
    }

    @Test
    @DisplayName("Alterar usuário passando Id que nao existe")
    void errorUpdateUsersNotExist() throws Exception {
        apiClient.putUser(token, "6uxuPdfgvmQhpEz1", toJson(fixture.updateUsers()))
                .then()
                .spec(ApiValidationSpecs.validateRegisterUsers());
    }

    @Test
    @DisplayName("Alterar usuário com corpo do request vazio")
    void errorUpdateUsersBodyEmpty() {
        apiClient.putUser(token, userId, "")
                .then()
                .spec(ApiValidationSpecs.validateBodyEmpty());
    }

    private static String toJson(UserDto dto) throws Exception {
        return new DefaultJackson2ObjectMapperFactory()
                .create(UserDto.class, "UTF-8")
                .writeValueAsString(dto);
    }
}

