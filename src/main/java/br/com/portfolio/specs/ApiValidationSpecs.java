package br.com.portfolio.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class ApiValidationSpecs {

    public static ResponseSpecification validateGetUsersList() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody("quantidade", greaterThan(0))
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .build();

    }

    public static ResponseSpecification validateGetUsersByIdParam(String userId) {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", equalTo(1))
                .expectBody("usuarios.size()", equalTo(1))
                .expectBody("usuarios[0]._id", equalTo(userId))
                .build();
    }

    public static ResponseSpecification validateGetUsersEmpy() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", equalTo(0))
                .expectBody("usuarios.size()", equalTo(0))
                .build();
    }

    public static ResponseSpecification validateGetUsersByAdministrador() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", greaterThan(0))
                .expectBody("usuarios.administrador", everyItem(equalTo("true")))
                .build();
    }

    public static ResponseSpecification validateGetUsersByNotAdministrador() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", greaterThan(0))
                .expectBody("usuarios.administrador", everyItem(equalTo("false")))
                .build();
    }

    public static ResponseSpecification validateGetUsersByPassword(String password) {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", greaterThan(0))
                .expectBody("usuarios.password", everyItem(containsString(password)))
                .build();
    }

    public static ResponseSpecification validateGetUsersByNome(String nome) {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", greaterThan(0))
                .expectBody("usuarios.nome", everyItem(containsString(nome)))
                .build();
    }

    public static ResponseSpecification validateGetUsersByEmail(String email) {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/users-list-response.json"))
                .expectBody("quantidade", greaterThan(0))
                .expectBody("usuarios.email", everyItem(equalTo(email)))
                .build();
    }

    public static ResponseSpecification validateGetUserById(String userId) {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody(matchesJsonSchemaInClasspath("schemas/user-response.json"))
                .expectBody("_id", equalTo(userId))
                .build();
    }

    public static ResponseSpecification validateGetUserByIdNotFound() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("message", equalTo("Usuário não encontrado"))
                .build();
    }

    public static ResponseSpecification validateGetSizeId() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("id", equalTo("id deve ter exatamente 16 caracteres alfanuméricos"))
                .build();
    }

    public static ResponseSpecification validateRegisterUsers() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_CREATED)
                .expectBody("message", equalTo("Cadastro realizado com sucesso"))
                .expectBody("_id", not(empty()))
                .build();
    }

    public static ResponseSpecification validateAdministratorNull() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("administrador", equalTo("administrador é obrigatório"))
                .build();
    }

    public static ResponseSpecification validatePasswordNull() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("password", equalTo("password é obrigatório"))
                .build();
    }

    public static ResponseSpecification validateEmailNull() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("email", equalTo("email é obrigatório"))
                .build();
    }

    public static ResponseSpecification validateNameNull() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("nome", equalTo("nome é obrigatório"))
                .build();
    }

    public static ResponseSpecification validateEmailAlreadyExist() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("message", equalTo("Este email já está sendo usadote"))
                .build();
    }

    public static ResponseSpecification validateUpdateUsers() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody("message", equalTo("Registro alterado com sucesso"))
                .build();
    }

    public static ResponseSpecification validateBodyEmpty() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("nome", equalTo("nome é obrigatório"))
                .expectBody("email", equalTo("email é obrigatório"))
                .expectBody("password", equalTo("password é obrigatório"))
                .expectBody("administrador", equalTo("administrador é obrigatório"))
                .build();
    }

    public static ResponseSpecification validateDeleteUser() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody("message", equalTo("Registro excluído com sucesso"))
                .build();
    }

    public static ResponseSpecification validateDeleteUserNotExist() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectBody("message", equalTo("Nenhum registro excluído"))
                .build();
    }

    public static ResponseSpecification validateDeleteUserWithCart() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .expectBody("message", equalTo("Não é permitido excluir usuário com carrinho cadastrado"))
                .build();
    }

}

