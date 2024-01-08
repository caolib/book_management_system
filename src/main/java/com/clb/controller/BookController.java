package com.clb.controller;

import com.clb.domain.Book;
import com.clb.domain.Result;
import com.clb.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result<List<Book>> getAll() {
        List<Book> all = bookService.getAll();
        log.debug("all: " + all);
        return Result.success(all);
    }
}
