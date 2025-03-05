package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.exception.UserException;
import com.example.demo.model.AuthUser;
import com.example.demo.repository.AuthUserRepository;
import com.example.demo.util.EmailSenderService;
import com.example.demo.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JwtToken tokenUtil;

    @Autowired
    private EmailSenderService emailSenderService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user = new AuthUser(userDTO);

        // ✅ Hashing password before saving to database
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        String token = tokenUtil.createToken(user.getUserId());
        authUserRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(), "Registered in Greeting App",
                "Hii " + user.getFirstName() + ",\n\nYou have been successfully registered!\n\n"
                        + "User Id: " + user.getUserId() + "\n"
                        + "First Name: " + user.getFirstName() + "\n"
                        + "Last Name: " + user.getLastName() + "\n"
                        + "Email: " + user.getEmail() + "\n"
                        + "Token: " + token);

        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> user = Optional.ofNullable(authUserRepository.findByEmail(loginDTO.getEmail()));

        // ✅ Secure password matching using BCrypt
        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            emailSenderService.sendEmail(user.get().getEmail(), "Logged in Successfully!",
                    "Hii " + user.get().getFirstName() + ",\n\nYou have successfully logged in into Greeting App!");
            return "Congratulations!! You have logged in successfully!";
        } else {
            throw new UserException("Sorry! Email or Password is incorrect!");
        }
    }
}
