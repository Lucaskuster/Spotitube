package Spotitube.controllers.services;

import java.util.UUID;

public class LoginService {

//    public boolean loginControl (String pwDb, String pwIn){
//        return pwDb.equals(pwIn);
//    }

    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }
}
