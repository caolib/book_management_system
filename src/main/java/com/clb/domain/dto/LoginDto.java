package com.clb.domain.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录的用户名和密码
 */
@Data
public class LoginDto implements Serializable {
    @Pattern(regexp = "^\\S{1,10}$")
    private String username;
    @Pattern(regexp = "^\\S{1,10}$")
    private String password;
}
