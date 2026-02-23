package br.com.portfolio;

import br.com.portfolio.dtos.UserDto;
import br.com.portfolio.fixtures.NewUsersFixture;
import br.com.portfolio.specs.ApiValidationSpecs;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostUsersTest extends BaseApiTest {

    @Test
    @DisplayName("Cadastrar usuário administrador")
    void registerUserAdministrator() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersAdministrator()))
                .then()
                .spec(ApiValidationSpecs.validateRegisterUsers());
    }

    @Test
    @DisplayName("Cadastrar usuário que nao e administrador")
    void registerUserNotAdministrator() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersNotAdministrator()))
                .then()
                .spec(ApiValidationSpecs.validateRegisterUsers());
    }

    @Test
    @DisplayName("Validar campo administrador como obrigatorio")
    void errorAdministratorIsMandatory() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersAdministratorNull()))
                .then()
                .spec(ApiValidationSpecs.validateAdministratorNull());
    }

    @Test
    @DisplayName("Validar campo email como obrigatorio")
    void errorEmailIsMandatory() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersEmailNull()))
                .then()
                .spec(ApiValidationSpecs.validateEmailNull());
    }

    @Test
    @DisplayName("Validar campo nome como obrigatorio")
    void errorNameIsMandatory() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersNameNull()))
                .then()
                .spec(ApiValidationSpecs.validateNameNull());
    }

    @Test
    @DisplayName("Validar campo password como obrigatorio")
    void errorPasswordIsMandatory() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersPasswordNull()))
                .then()
                .spec(ApiValidationSpecs.validatePasswordNull());
    }

    @Test
    @DisplayName("Cadastrar usuário com email que ja esta sendo usado")
    void errorEmailAlreadyExist() throws Exception {
        apiClient.postUser(token, toJson(NewUsersFixture.getUsersEmailAlreadyExist()))
                .then()
                .spec(ApiValidationSpecs.validateEmailAlreadyExist());
    }

    @Test
    @DisplayName("Cadastrar passando o corpo da requisicao vazio")
    void errorRegistreDataUserEmpty() {
        apiClient.postUser(token, "")
                .then()
                .spec(ApiValidationSpecs.validateBodyEmpty());
    }

    private static String toJson(UserDto dto) throws Exception {
        return new DefaultJackson2ObjectMapperFactory()
                .create(UserDto.class, "UTF-8")
                .writeValueAsString(dto);
    }
}

