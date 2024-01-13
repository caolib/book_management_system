package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Admin;
import com.clb.domain.vo.AdminVo;
import com.clb.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminLoginController {
    private final AdminService adminService;

    @PostMapping("/login")
    public Result<AdminVo>login(@RequestBody LoginDto admin){
        log.debug("admin:{}", admin);

        return adminService.login(admin);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody Admin admin) {
        log.debug("admin{}", admin);

        return adminService.register(admin);
    }

}
