package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserService(UserRepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    @Transactional
    public void register(UserDTO userDTO) {
        Optional<User> existingUser = Optional.ofNullable(this.userRepository.findByUsername(userDTO.getUsername()));
        if (existingUser.isPresent()) {
            throw new DuplicateRequestException(String.format("User with username '%s' already exists.", userDTO.getUsername()));
        }
        String password = encoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getUsername(), password, userDTO.getEmail(), userDTO.getRole());
        userRepository.save(user);
    }

    public String verify(UserDTO user) throws UserNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return jwtService.generateToken((UserDetails) authentication.getPrincipal());
        } catch (AuthenticationException ex) {
            throw new UserNotFoundException((String.format("User with username %s not register", user.getUsername())), ex);
        }
    }
}
