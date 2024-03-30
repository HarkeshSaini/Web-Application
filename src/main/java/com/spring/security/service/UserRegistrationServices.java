package com.spring.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.entity.DeleteUserInfo;
import com.spring.security.entity.UserRegistration;
import com.spring.security.interfaces.UserRegisterDetaill;
import com.spring.security.repositories.DeleteUserInfoRepositorie;
import com.spring.security.repositories.UserRegistrationRepositorie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationServices implements UserRegisterDetaill {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public ObjectMapper mapper;

    @Autowired
    private UserRegistrationRepositorie userRegistrationRepositorie;

    @Autowired
    private DeleteUserInfoRepositorie deleteUserInfoRepositorie;

    @Override
    public List<UserRegistration> getAllserRegistration(){
       List<UserRegistration> allUserRes = userRegistrationRepositorie.findAll();
       return allUserRes;
    }

    @Override
    public UserRegistration findByIdRegistrationUser(Integer id){
        var findByIdRegistrationUser = userRegistrationRepositorie.findById(id);
        var userRegistration = findByIdRegistrationUser.get();
        return userRegistration;
    }

    @Override
    public ResponseEntity<String> deleteUserRegistrationInfo(Integer id) {
        UserRegistration userRegistration= findByIdRegistrationUser(id);
        final var deleteUserInfo = mapper.convertValue(userRegistration, DeleteUserInfo.class);
        deleteUserInfoRepositorie.save(deleteUserInfo);
        userRegistrationRepositorie.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> undoUserRegistrationInfoByUserId(Integer id){
        deleteUserInfoRepositorie.findById(id);
        return ResponseEntity.ok("UserInfo Undo successfully");
    }
}
