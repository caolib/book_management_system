package com.clb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    @TableId
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$")
    private String username;
    @Pattern(regexp = "^\\S{1,10}$")
    private String password;
    @Pattern(regexp = "^\\S{0,16}$")
    private String nickname;
}
