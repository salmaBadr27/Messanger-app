package com.mycompany.salma33.Utility;

import com.mycompany.salma33.Application.AuthenticationError;
import com.mycompany.salma33.Application.DataBaseError;
import com.mycompany.salma33.Database.UserRepository;
import com.mycompany.salma33.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

public class Authentication {

    private final String secret;
    Gson gson = new Gson();

    public Authentication(String secretKey) {
        this.secret = secretKey;
    }

    public String generateToken(String data) {
        String compactJws = Jwts.builder()
                .setPayload(data)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }

    public User AuthenticateWithJson(UserRepository ur, String userinfo) throws AuthenticationError, DataBaseError {

        try {
            User fakeUser = gson.fromJson(userinfo, User.class);
            User realUser = ur.getUserByUsername(fakeUser.getUserName());
            if (realUser.getPassword().equals(fakeUser.getPassword())) {
                return realUser;
            } else {
                throw new AuthenticationError("Password not valid");
            }

        } catch (DataBaseError | AuthenticationError e) {
            System.out.print("caught in auth " + e);
            throw e;
        }
    }

    public User AuthenticateWithToken(UserRepository ur, String Token) throws AuthenticationError, DataBaseError {
        try {
            String[] split_string = Token.split("\\.");
            String Header = split_string[0];
            String Payload = split_string[1];
            String Signature = split_string[2];
            Base64 base64Url = new Base64(true);
            String body = new String(base64Url.decode(Payload));
            User user = AuthenticateWithJson(ur, body);
            return user;
        } catch (AuthenticationError | DataBaseError | Exception e) {
            System.out.print("caught in auth with token " + e);
            throw e;
        }
    }
}
