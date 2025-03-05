package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.AuthUser;

public interface IAuthenticationService {
    AuthUser register(AuthUserDTO userDTO) throws Exception;

    String login(LoginDTO loginDTO);
}
