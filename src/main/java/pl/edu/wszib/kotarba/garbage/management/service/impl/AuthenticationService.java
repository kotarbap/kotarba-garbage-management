package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.garbage.management.database.IUserDAO;
import pl.edu.wszib.kotarba.garbage.management.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.kotarba.garbage.management.model.User;
import pl.edu.wszib.kotarba.garbage.management.model.view.RegisterUser;
import pl.edu.wszib.kotarba.garbage.management.service.IAuthenticationService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPass().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.userDAO.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) {
            throw new LoginAlreadyUseException();
        }

        registerUser.setPass(DigestUtils.md5Hex(registerUser.getPass()));

        User user = new User();
        user.setLogin(registerUser.getLogin());
        user.setPass(registerUser.getPass());
        user.setSurname(registerUser.getSurname());
        user.setName(registerUser.getName());

        this.userDAO.addUser(user);
    }
}
