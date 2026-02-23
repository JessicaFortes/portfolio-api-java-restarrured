package br.com.portfolio.fixtures;
import br.com.portfolio.dtos.UserDto;
import net.datafaker.Faker;

public class NewUsersFixture {

    private static final Faker FAKER = new Faker();

    public static UserDto getUsersAdministrator() {
        String nome = FAKER.name().fullName();
        String email = ("user." + System.currentTimeMillis() + "@qa.com").toLowerCase();
        String password = "teste";
        String administrador = "true";

        return UserDto.getUsers(nome, email, password, administrador);
    }

    public static UserDto getUsersNotAdministrator() {
        String nome = FAKER.name().fullName();
        String email = ("user." + System.currentTimeMillis() + "@qa.com").toLowerCase();
        String password = "teste";
        String administrador = "false";

        return UserDto.getUsers(nome, email, password, administrador);
    }

    public static UserDto getUsersAdministratorNull() {
        String nome = FAKER.name().fullName();
        String email = ("user." + System.currentTimeMillis() + "@qa.com").toLowerCase();
        String password = "teste";

        return UserDto.getUsersAdministratorNull(nome, email, password);
    }

    public static UserDto getUsersNameNull() {
        String email = ("user." + System.currentTimeMillis() + "@qa.com").toLowerCase();
        String password = "teste";
        String administrador = "false";

        return UserDto.getUsersNameNull(email, password, administrador);
    }

    public static UserDto getUsersEmailNull() {
        String nome = FAKER.name().fullName();
        String password = "teste";
        String administrador = "true";

        return UserDto.getUsersEmailNull(nome, password, administrador);
    }

    public static UserDto getUsersPasswordNull() {
        String nome = FAKER.name().fullName();
        String email = ("user." + System.currentTimeMillis() + "@qa.com").toLowerCase();
        String administrador = "true";

        return UserDto.getUsersPasswordNull(nome, email, administrador);
    }

    public static UserDto getUsersEmailAlreadyExist() {
        String nome = FAKER.name().fullName();
        String email = "portfolio-api-jess@qa.com.br";
        String password = "teste";
        String administrador = "true";

        return UserDto.getUsers(nome, email, password, administrador);
    }

}

