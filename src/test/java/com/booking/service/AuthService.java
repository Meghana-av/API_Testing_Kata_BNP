package com.booking.service;

import com.booking.client.AuthClient;
import com.booking.models.AuthRequest;
import com.booking.utils.ConfigReader;

public class AuthService {

    private AuthClient authClient = new AuthClient();

    public String getAuthToken() {

        String username = ConfigReader.get("auth.username");
        String password = ConfigReader.get("auth.password");

        AuthRequest authRequest =
                new AuthRequest(username, password);

        return authClient.getToken(authRequest);
    }
}