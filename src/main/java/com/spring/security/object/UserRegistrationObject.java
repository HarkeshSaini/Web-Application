package com.spring.security.object;

import lombok.Data;
@Data
public class UserRegistrationObject {

    private int id;
    private String userName;
    private String userPass;
    private String userEmail;
    private String siteId;
    private String roleId;
    private String siteName;
    private String createDate;
    private String emailPass;

}
