package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.request.UserRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping
    private UserDTO createUser(@RequestBody UserRequest userRequest)
    {
        UserDTO userDTO = userService.createUser(userRequest);
        return userDTO;
    }

    @PutMapping(value = "{id}")
    private UserDTO updateUser(@RequestBody UserRequest userRequest)
    {
        UserDTO userDTO = userService.updateUser(userRequest);
        return userDTO;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/{id}")
    private Object getUserById(@PathVariable("id") Long id, UserDTO userDTO){

        UserEntity userEntity = userService.getUserById(id);

        UserRequest userRequest = new UserRequest();

        userRequest.setName(userEntity.getName());
        userRequest.setLastName(userEntity.getLastName());
        userRequest.setId(userEntity.getId());
        userRequest.setAddress(userEntity.getAddress());

        return userDTO;
    }
}
