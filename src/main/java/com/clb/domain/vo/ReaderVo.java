package com.clb.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 读者视图对象，包含token
 */
@Data
public class ReaderVo implements Serializable {
    private String id;
    private String username;
    private String nickname;
    private String gender;
    private Integer age;
    private String tel;
    private String password;
    private String token;
}
