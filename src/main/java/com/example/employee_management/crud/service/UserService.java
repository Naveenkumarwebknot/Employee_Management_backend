package com.example.employee_management.crud.service;


import com.example.employee_management.crud.model.UserApp;
import com.example.employee_management.crud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import error.UserNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserApp> fetchAllUsers(){
        return userRepository.findAll();
    }

    public UserApp saveUser(UserApp user) {

        return userRepository.save(user);
    }

    public UserApp fetchUserById(Long Id) throws UserNotFoundException {
        Optional<UserApp> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }
        return userRecord.get();
    }

    public UserApp updateUser(Long tId, UserApp user) throws UserNotFoundException {
        Optional<UserApp> isExisting = userRepository.findById(tId);

        if (!isExisting.isPresent()) {
            throw new UserNotFoundException("Employee with id:"+tId+" not found !!");
        }

        UserApp userDB = isExisting.get();

        if(Objects.nonNull(user.getUserName()) &&
                !"".equalsIgnoreCase(user.getUserName())) {
            userDB.setUserName(user.getUserName());
        }
        if(Objects.nonNull(user.getUserPassword()) &&
                !"".equalsIgnoreCase(user.getUserPassword())) {
            userDB.setUserPassword(user.getUserPassword());
        }

        return (UserApp) userRepository.save(userDB);
    }

    //Delete a record from the table
    public String deleteUser(Long Id) throws UserNotFoundException {
        Optional<UserApp> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }

        userRepository.deleteById(Id);
        return ("User with id:"+Id+" successfully deleted !!");
    }

}