package com.clb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Page<Book> getBookPage(Condition condition);

    void deleteBookByIsbn(Integer isbn);
}
