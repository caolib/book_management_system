package com.clb.controller;

import com.clb.constant.Cache;
import com.clb.domain.Result;
import com.clb.domain.entity.Reader;
import com.clb.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }


    /**
     * 查询所有用户信息
     */
    @PostMapping
    @Cacheable(cacheNames = Cache.READER, key = "#condition")
    public Result<List<Reader>> getAllReader(@RequestBody Reader condition) {
        log.debug("condition:{}", condition);

        return readerService.getAllReader(condition);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    @CacheEvict(value = Cache.READER, allEntries = true)
    public Result<Reader> updateReader(@RequestBody @Validated Reader reader) {
        log.info("reader:{}", reader);
        return readerService.updateReader(reader);
    }

    /**
     * 根据id删除用户
     */
    @DeleteMapping("/{id}")
    @CacheEvict(value = Cache.READER, allEntries = true)
    public Result<String> deleteById(@PathVariable Integer id) {
        log.debug("id:{}", id);

        return readerService.deleteById(id);
    }

}
