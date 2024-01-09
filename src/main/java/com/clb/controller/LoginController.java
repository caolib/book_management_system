package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.dto.ReaderDto;
import com.clb.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final ReaderService readerService;

    @PostMapping ("/login")
    public Result<ReaderDto> login(@RequestBody ReaderDto reader) {
        log.debug("reader:{}",reader);
        return readerService.login(reader);
    }

}
