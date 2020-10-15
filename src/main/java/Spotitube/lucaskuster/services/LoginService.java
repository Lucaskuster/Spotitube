package Spotitube.lucaskuster.services;

import java.util.UUID;

public class LoginService {

    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }
}
