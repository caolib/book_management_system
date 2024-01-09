package com.clb.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reader {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private Integer age;
    private String tel;
}
