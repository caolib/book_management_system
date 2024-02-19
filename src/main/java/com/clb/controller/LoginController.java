package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Reader;
import com.clb.domain.vo.ReaderVo;
import com.clb.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/reader")
public class LoginController {
    private final ReaderService readerService;

    /**
     * 读者登录
     *
     * @param reader 用户名、密码
     * @return 返回
     */
    @PostMapping("/login")
    public Result<ReaderVo> login(@RequestBody @Validated LoginDto reader) {
        log.debug("reader:{}", reader);
        return readerService.login(reader);
    }

    /**
     * 用户注册
     *
     * @param reader 用户注册信息
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated Reader reader) {
        log.debug("register-reader:{}", reader);

        return readerService.register(reader);
    }

}
