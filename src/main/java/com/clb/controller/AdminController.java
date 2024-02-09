package com.clb.controller;

import com.clb.annotation.MyController;
import com.clb.domain.Result;
import com.clb.domain.entity.Admin;
import com.clb.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@MyController(prefix = "/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;


    /**
     * 更新管理员信息
     *
     * @param admin 管理员对象
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Admin admin) {
        log.info("admin:{}", admin);

        return adminService.updateById(admin);
    }

}
