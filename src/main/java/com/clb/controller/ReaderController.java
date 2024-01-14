package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.entity.Reader;
import com.clb.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderService readerService;
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping
    public Result<List<Reader>> getAllReader(@RequestBody Reader condition) {
        log.debug("condition:{}", condition);

        return readerService.getAllReader(condition);
    }


    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    public Result<Reader> updateReader(@RequestBody Reader reader) {
        log.info("reader:{}", reader);
        return readerService.updateReader(reader);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable Integer id) {
        log.debug("id:{}", id);

        return readerService.deleteById(id);
    }

}
