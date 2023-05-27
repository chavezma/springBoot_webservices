package com.tutorial.webservices.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

    UserRepository userRepository;

    public UserDaoService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User retrieveById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(UserDTO user) {
        return userRepository.save(new User(user.getId(), user.getName(), user.getBithDate()));
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
