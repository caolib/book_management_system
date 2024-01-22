package com.clb.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回前端的管理员视图对象，包含token
 */

@Data
public class AdminVo implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String token;
}
