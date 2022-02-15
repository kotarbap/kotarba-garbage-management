package pl.edu.wszib.kotarba.garbage.management.service;

import pl.edu.wszib.kotarba.garbage.management.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
