package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Admin;
import com.clb.domain.vo.AdminVo;
import com.clb.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AdminLoginController {
    private final AdminService adminService;

    /**
     * 管理员登录
     *
     * @param admin 用户名和密码
     * @return 管理员相关信息，包含token
     */
    @PostMapping("/login")
    public Result<AdminVo> login(@RequestBody @Validated LoginDto admin) {
        log.debug("admin:{}", admin);

        return adminService.login(admin);
    }

    /**
     * 管理员注册
     *
     * @param admin 用户名、密码和昵称
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated Admin admin) {
        log.debug("admin{}", admin);

        return adminService.register(admin);
    }

}
