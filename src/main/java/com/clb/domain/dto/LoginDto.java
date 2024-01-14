package com.clb.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    String username;
    String password;
}
