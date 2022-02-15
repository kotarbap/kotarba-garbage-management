package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.kotarba.garbage.management.configuration.TestConfiguration;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.database.ITruckDAO;
import pl.edu.wszib.kotarba.garbage.management.database.IUserDAO;
import pl.edu.wszib.kotarba.garbage.management.model.User;
import pl.edu.wszib.kotarba.garbage.management.service.IAuthenticationService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
public class AuthenticationServiceTest {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBagDAO bagDAO;

    @MockBean
    ITruckDAO truckDAO;

    @Test
    public void correctAuthTest() {
        Mockito.when(this.userDAO.getUserByLogin(Mockito.anyString())).thenReturn(generateUser());
        String login = "piotr";
        String pass = "piotr";

        this.authenticationService.authenticate(login, pass);

        Assert.assertTrue(this.sessionObject.isLogged());
    }

    @Test
    public void incorrectAuthTest() {
        Mockito.when(this.userDAO.getUserByLogin(Mockito.anyString())).thenReturn(Optional.empty());
        String login = "pioadgdhtr";
        String pass = "pioahddhtr";

        this.authenticationService.authenticate(login, pass);

        Assert.assertFalse(this.sessionObject.isLogged());
    }

    public Optional<User> generateUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("piotr");
        user.setPass("99fdb06613cd9b8f328b6cadd98b1c23");
        user.setName("adgd");
        user.setSurname("agsg");

        return Optional.of(user);
    }
}
