package com.spring.security.interfaces;

import com.spring.security.entity.DeleteUserInfo;
import com.spring.security.entity.UserRegistration;
import com.spring.security.object.UserRegistrationObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRegisterDetaill {
    List<UserRegistration> getAllserRegistration();
    UserRegistration findByIdRegistrationUser(Integer id);
    public ResponseEntity<String> deleteUserRegistrationInfo(Integer id);
    ResponseEntity<String> undoUserRegistrationInfoByUserId(Integer id);
    public UserRegistration submitNewUserRegistration(UserRegistrationObject userRegistrationObject);
}
