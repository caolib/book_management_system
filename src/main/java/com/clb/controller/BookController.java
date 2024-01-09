package com.clb.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clb.domain.PageResult;
import com.clb.domain.Result;
import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;
import com.clb.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    //@GetMapping
    //public Result<List<Book>> getAll() {
    //    List<Book> all = bookService.getAll();
    //    log.debug("all: " + all);
    //    return Result.success(all);
    //}

    @PostMapping
    public PageResult<List<Book>> getBookPage(@RequestBody Condition condition) {
        log.info("查询条件:{}",condition);

        Page<Book> bookPage = bookService.getBookPage(condition);

        List<Book> data = bookPage.getRecords();
        Long total = bookPage.getTotal();

        return PageResult.success(data, total);
    }

    @DeleteMapping("/{isbn}")
    public Result<String> deleteBookByIsbn(@PathVariable Integer isbn) {
        bookService.deleteBookByIsbn(isbn);
        return Result.success();
    }
}
