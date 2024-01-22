package com.clb.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录的用户名和密码
 */
@Data
public class LoginDto implements Serializable {
    String username;
    String password;
}
