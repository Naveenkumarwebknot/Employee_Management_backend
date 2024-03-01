package com.example.employee_management.crud.service;


import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.model.UserApp;
import com.example.employee_management.crud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import error.UserNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Cacheable(value = "Users")
    public List<UserApp> fetchAllUsers(){
        return userRepository.findAll();
    }

    public UserApp saveUser(UserApp user) {

        return userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#id")
    public UserApp fetchUserById(Long Id) throws UserNotFoundException {
        Optional<UserApp> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }
        return userRecord.get();
    }
    @Caching(
            evict = {@CacheEvict(value = "users", allEntries = true)},
            put = {@CachePut(value = "users", key = "#Blog.id")}
    )
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
    @CacheEvict(cacheNames = "users", allEntries = true)
    public String deleteUser(Long Id) throws UserNotFoundException {
        Optional<UserApp> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }

        userRepository.deleteById(Id);
        return ("User with id:"+Id+" successfully deleted !!");
    }

    public List<UserApp> findUserWithSorting(String field){
        return  userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<UserApp> findUserWithPagination(int offset, int pageSize){
        Page<UserApp> users = userRepository.findAll(PageRequest.of(offset, pageSize));
        return  users;
    }

    public Page<UserApp> findUserWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<UserApp> users = userRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  users;
    }

}