package com.tutorial.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable Integer id){
        User selectedUser = userDaoService.retrieveById(id);

        if( Objects.isNull(selectedUser) )
            throw new UserNotFoundException(
                String.format("Usuario con id [%s] no encontrado", id.toString()));

        return ResponseEntity.ok().body(selectedUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User savedUser = userDaoService.createUser(user);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        User selectedUser = retrieveUser(id).getBody();
        userDaoService.deleteUserById(id);

        return ResponseEntity.ok().body(selectedUser);
    }
}
