package br.com.portfolio.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("administrador")
    private String administrador;

    @JsonProperty("_id")
    private String id;

    public static UserDto getUsers(String nome, String email, String password, String administrador){
        return UserDto.builder()
                .nome(nome)
                .email(email)
                .password(password)
                .administrador(administrador)
                .build();
    }

    public static UserDto getUsersAdministratorNull(String nome, String email, String password){
        return UserDto.builder()
                .nome(nome)
                .email(email)
                .password(password)
                .build();
    }

    public static UserDto getUsersNameNull(String email, String password, String administrador){
        return UserDto.builder()
                .administrador(administrador)
                .email(email)
                .password(password)
                .build();
    }

    public static UserDto getUsersEmailNull(String nome, String password, String administrador){
        return UserDto.builder()
                .nome(nome)
                .password(password)
                .administrador(administrador)
                .build();
    }

    public static UserDto getUsersPasswordNull(String nome, String email, String administrador){
        return UserDto.builder()
                .nome(nome)
                .email(email)
                .administrador(administrador)
                .build();
    }
}

