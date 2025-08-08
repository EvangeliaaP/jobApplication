package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    public UserService(UserRepository repository, ModelMapper mapper){
        this.userRepository = repository;
        this.mapper = mapper;
    }

    public List<UserDTO> getAllUsers(){
        return this.userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void createUser(UserDTO userDTO){
        this.userRepository.save(this.convertToEntity(userDTO));
    }

    private UserDTO convertToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO){
        return this.mapper.map(userDTO, User.class);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
