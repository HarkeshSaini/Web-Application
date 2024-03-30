package com.spring.security.interfaces;

import com.spring.security.entity.DeleteUserInfo;
import com.spring.security.entity.UserRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRegisterDetaill {
    List<UserRegistration> getAllserRegistration();
    UserRegistration findByIdRegistrationUser(Integer id);
    public ResponseEntity<String> deleteUserRegistrationInfo(Integer id);
    ResponseEntity<String> undoUserRegistrationInfoByUserId(Integer id);
}
