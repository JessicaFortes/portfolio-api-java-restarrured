package br.com.portfolio;

import br.com.portfolio.dtos.UserDto;
import br.com.portfolio.fixtures.UpdateUsersFixture;
import br.com.portfolio.helpers.HelperFixture;
import br.com.portfolio.specs.ApiValidationSpecs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetUsersTest extends BaseApiTest {
    private static UserDto user;

    @BeforeAll
    static void loadUser() {
        user = HelperFixture.getUser("consulta");
        user.setId(HelperFixture.getUserId("consulta"));
    }

    @Test
    @DisplayName("Buscar todos os usuários")
    void shouldListUsers() {
        apiClient.getUsers(token).then().spec(ApiValidationSpecs.validateGetUsersList());
    }

    @Test
    @DisplayName("Buscar usuários por id")
    void searchForUserById() {
        apiClient.getUsers(token, Map.of("_id", user.getId()))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByIdParam(user.getId()));
    }

    @Test
    @DisplayName("Buscar usuários por nome")
    void searchForUserByName() {
        apiClient.getUsers(token, Map.of("nome", user.getNome()))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByNome(user.getNome()));
    }

    @Test
    @DisplayName("Buscar usuários por senha")
    void searchForUserByPassword() {
        apiClient.getUsers(token, Map.of("password", user.getPassword()))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByPassword(user.getPassword()));
    }

    @Test
    @DisplayName("Buscar usuários administrador")
    void searchForUserByAdministrator() {
        apiClient.getUsers(token, Map.of("administrador", "true"))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByAdministrador());
    }

    @Test
    @DisplayName("Buscar usuários que nao sao administradores")
    void searchForUserByNotAdministrator() {
        apiClient.getUsers(token, Map.of("administrador", "false"))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByNotAdministrador());
    }

    @Test
    @DisplayName("Buscar usuários por email")
    void searchForUserByEmail() {
        apiClient.getUsers(token, Map.of("email", user.getEmail()))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersByEmail(user.getEmail()));
    }

    @Test
    @DisplayName("Validar retorno ao passar id de usuário inexistente")
    void searchForUserNotExist() {
        apiClient.getUsers(token, Map.of("_id", "123abc"))
                .then()
                .spec(ApiValidationSpecs.validateGetUsersEmpy());
    }
}

