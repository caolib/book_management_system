package com.clb.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clb.constant.Excep;
import com.clb.domain.Borrow;
import com.clb.domain.Result;
import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;
import com.clb.exception.AlreadyExistException;
import com.clb.exception.BaseException;
import com.clb.mapper.BookMapper;
import com.clb.mapper.BorrowMapper;
import com.clb.service.BookService;
import com.clb.util.MyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BorrowMapper borrowMapper;

    @Override
    public Page<Book> getBookPage(Condition condition) {
        // 取出参数
        String isbn = condition.getIsbn();
        String bookName = condition.getBookName();
        String author = condition.getAuthor();
        Integer currentPage = condition.getCurrentPage();
        Integer pageSize = condition.getPageSize();

        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        // 根据搜索条件查询
        queryWrapper
                .eq(MyUtils.StrUtil(isbn), Book::getIsbn, isbn)
                .like(MyUtils.StrUtil(bookName), Book::getTitle, bookName)
                .like(MyUtils.StrUtil(author), Book::getAuthor, author);
        // 分页查询
        return bookMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        // 查找借阅表中是否有该书籍信息
        LambdaQueryWrapper<Borrow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Borrow::getIsbn, isbn);
        List<Borrow> borrows = borrowMapper.selectList(queryWrapper);
        // 如果书籍信息存在于借阅表中，抛出业务异常，不允许删除
        if (!borrows.isEmpty()) {
            throw new AlreadyExistException(Excep.DELETE_BOOK_NOT_ALLOW);
        }

        bookMapper.deleteById(isbn);
    }

    @Override
    public Result<String> add(Book book) {
        String isbn = book.getIsbn();
        String title = book.getTitle();
        Integer number = book.getNumber();
        //isbn不能为空
        if (!MyUtils.StrUtil(isbn)) {
            throw new BaseException(Excep.ISBN_IS_NULL);
        }
        //书名不能为空
        if (!MyUtils.StrUtil(title)) {
            throw new BaseException(Excep.TITLE_IS_NULL);
        }
        // 库存量大于等于0
        if (number == null || number < 0) {
            throw new BaseException(Excep.BOOK_NUMBER_ERROR);
        }

        // 查找isbn是否存在
        Long l = bookMapper.getByIsbn(isbn);
        if (l > 0) {
            throw new AlreadyExistException(Excep.ISBN_ALREADY_EXIST);
        }

        bookMapper.insert(book);

        return Result.success();
    }

    @Override
    public Result<String> updateBook(Book book) {
        String isbn = book.getIsbn();
        //查询isbn是否存在
        Book b = bookMapper.selectById(isbn);
        if (b == null) {
            throw new BaseException(Excep.ISBN_NOT_EXIST);
        }

        bookMapper.updateById(book);

        return Result.success();
    }
}
