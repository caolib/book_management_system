package com.clb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clb.domain.Result;
import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;

import java.util.List;

public interface BookService {
    Page<Book> getBookPage(Condition condition);

    void deleteBookByIsbn(Integer isbn);

    Result<String> add(Book book);
}
