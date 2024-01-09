package com.clb.service.Impl;

import com.clb.domain.entity.Book;
import com.clb.mapper.BookMapper;
import com.clb.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book>getAll(){
        return bookMapper.selectList(null);
    }
}
