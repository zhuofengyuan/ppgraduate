package com.fengtoos.ppgraduate.auth.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class PrincipalController {

    private final static String ACCESS_TOKEN = "access_token";

    @GetMapping("/principal")
    public Principal getPrincipal(Principal user) {
        return user;
    }
}
