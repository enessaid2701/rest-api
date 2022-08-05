package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getAll()
    {
        Iterator<UserEntity> usersIt = userRepository.findAll().iterator();
        List<UserEntity> users = new ArrayList<>();

        usersIt.forEachRemaining(users::add);

        return users;
    }

    public UserEntity getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public UserDTO createUser(UserRequest userRequest)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setName(userRequest.getName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setId(userRequest.getId());

        userRepository.save(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setAddress(userEntity.getAddress());

        return userDTO;
    }

    public UserDTO updateUser(UserRequest userRequest)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userRequest.getId());
        userEntity.setName(userRequest.getName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setAddress(userRequest.getAddress());

        userRepository.save(userEntity);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setAddress(userEntity.getAddress());

        return userDTO;
    }

    public void deleteUser(Long id)
    {
        Optional<UserEntity> studentEntityOptional = userRepository.findById(id);
        studentEntityOptional.ifPresent(studentEntity -> userRepository.delete(studentEntity));
    }
}
