package com.clb.controller;

import com.clb.domain.Result;
import com.clb.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/update/{id}/{nickname}")
    public Result<String> update(@PathVariable Integer id, @PathVariable String nickname) {
        log.info("id:{} nickname:{}", id, nickname);

        return adminService.updateNicknameById(id, nickname);
    }

}
