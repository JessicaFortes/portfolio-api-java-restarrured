package br.com.portfolio.fixtures;

import br.com.portfolio.dtos.UserDto;
import br.com.portfolio.helpers.HelperFixture;
import net.datafaker.Faker;

public class UpdateUsersFixture {

    private static final Faker FAKER = new Faker();
    private final UserDto user;

    public UpdateUsersFixture() {
        this.user = HelperFixture.getUser("alteracao");
    }

    public UserDto updateOnlyEmail() {
        user.setEmail(("user." + System.currentTimeMillis() + "@qa.com").toLowerCase());
        return user;
    }

    public UserDto updateOnlyPassword() {
        user.setPassword("novaSenha");
        return user;
    }

    public UserDto updateOnlyNome() {
        user.setNome(FAKER.name().fullName());
        return user;
    }

    public UserDto updateOnlyAdministrador() {
        user.setAdministrador("false");
        return user;
    }

    public UserDto updateUsers() {
        user.setNome(FAKER.name().fullName());
        user.setEmail(("user." + System.currentTimeMillis() + "@qa.com").toLowerCase());
        user.setPassword("newTest");
        user.setAdministrador("false");
        return user;
    }
}

