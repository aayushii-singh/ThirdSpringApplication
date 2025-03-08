package com.example.demo.controller;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.dto.LoginDTO;
import java.util.Map;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.AuthUser;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody AuthUserDTO userDTO) throws Exception{
        AuthUser user=authenticationService.register(userDTO);
        ResponseDTO responseUserDTO =new ResponseDTO("User details is submitted!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        String result=authenticationService.login(loginDTO);
        ResponseDTO responseUserDTO=new ResponseDTO("Login successfully!!",result);
        return  new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    @PutMapping("/auth/forgotPassword/{email}")
    public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        String message = authenticationService.forgotPassword(email, newPassword);
        return new ResponseEntity<>(new ResponseDTO(message, null), HttpStatus.OK);
    }

    @PutMapping("/auth/resetPassword/{email}")
    public ResponseEntity<ResponseDTO> resetPassword(@PathVariable String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        String message = authenticationService.resetPassword(email, currentPassword, newPassword);
        return new ResponseEntity<>(new ResponseDTO(message, null), HttpStatus.OK);
    }
}