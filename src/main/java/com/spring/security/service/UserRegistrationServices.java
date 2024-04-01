package com.spring.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.entity.DeleteUserInfo;
import com.spring.security.entity.UserRegistration;
import com.spring.security.interfaces.UserRegisterDetaill;
import com.spring.security.object.UserRegistrationObject;
import com.spring.security.repositories.DeleteUserInfoRepositorie;
import com.spring.security.repositories.UserRegistrationRepositorie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
       var allUserRes = userRegistrationRepositorie.findAll();
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
        var userRegistration= findByIdRegistrationUser(id);
        var deleteUserInfo = modelMapper.map(userRegistration, DeleteUserInfo.class);
        deleteUserInfoRepositorie.save(deleteUserInfo);
        userRegistrationRepositorie.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> undoUserRegistrationInfoByUserId(Integer id){
        var userRegistration= deleteUserInfoRepositorie.findById(id).get();
        var undoUserReg = modelMapper.map(userRegistration, UserRegistration.class);
        userRegistrationRepositorie.save(undoUserReg);
        deleteUserInfoRepositorie.findById(id);
        return ResponseEntity.ok("UserInfo Undo successfully");
    }

    @Override
    public UserRegistration submitNewUserRegistration(UserRegistrationObject userRegistrationObject){
        var newRegistration = modelMapper.map(userRegistrationObject ,UserRegistration.class);
        return userRegistrationRepositorie.save(newRegistration);
    }
}
