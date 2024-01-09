package com.clb.controller;

import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;
import com.clb.domain.Result;
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

    @GetMapping("/page")
    public Result<List<Book>> getByPage(@RequestBody Condition condition) {
        

        return Result.success();
    }

    @GetMapping
    public Result<List<Book>> getAll() {
        List<Book> all = bookService.getAll();
        log.debug("all: " + all);
        return Result.success(all);
    }


}
