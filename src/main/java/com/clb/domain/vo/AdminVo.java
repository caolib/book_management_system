package com.clb.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminVo implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String token;
}
