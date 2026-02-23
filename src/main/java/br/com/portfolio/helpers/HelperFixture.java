package br.com.portfolio.helpers;

import br.com.portfolio.dtos.UserDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelperFixture {

    public static String loadFixture(String fixtureName) {
        Path filePath = Path.of(System.getProperty("user.dir"), "data", fixtureName);
        try {
            if (!Files.exists(filePath)) {
                throw new RuntimeException("Arquivo fixture '" + fixtureName + "' nao encontrado em " + filePath);
            }
            return Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler fixture '" + fixtureName + "': " + e.getMessage(), e);
        }
    }

    public static String getUserId(String massa) {
        String content = loadFixture("users.json");
        return io.restassured.path.json.JsonPath.from(content).getString(massa + "._id");
    }

    public static UserDto getUser(String massa) {
        String content = loadFixture("users.json");
        String nome = io.restassured.path.json.JsonPath.from(content).getString(massa + ".nome");
        String email = io.restassured.path.json.JsonPath.from(content).getString(massa + ".email");
        String password = io.restassured.path.json.JsonPath.from(content).getString(massa + ".password");
        String administrador = io.restassured.path.json.JsonPath.from(content).getString(massa + ".administrador");
        return UserDto.getUsers(nome, email, password, administrador);
    }
}

