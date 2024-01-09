package com.clb.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clb.constant.Excep;
import com.clb.domain.Borrow;
import com.clb.domain.dto.Condition;
import com.clb.domain.entity.Book;
import com.clb.exception.AlreadyExistException;
import com.clb.mapper.BookMapper;
import com.clb.mapper.BorrowMapper;
import com.clb.service.BookService;
import com.clb.util.MyUtil;
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

    public List<Book> getAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public Page<Book> getBookPage(Condition condition) {
        // 取出参数
        String isbn = condition.getISBN();
        String bookName = condition.getBookName();
        String author = condition.getAuthor();
        Integer currentPage = condition.getCurrentPage();
        Integer pageSize = condition.getPageSize();

        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        // 根据搜索条件查询
        queryWrapper
                .eq(MyUtil.StrUtil(isbn), Book::getIsbn, isbn)
                .like(MyUtil.StrUtil(bookName), Book::getTitle, bookName)
                .like(MyUtil.StrUtil(author), Book::getAuthor, author);

        // 分页
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(currentPage,pageSize), queryWrapper);

        List<Book> records = bookPage.getRecords();
        long total = bookPage.getTotal();

        log.info("records:{}", records);
        log.info("total:{}", total);

        return bookPage;
    }

    @Override
    public void deleteBookByIsbn(Integer isbn) {
        // 先查找借阅表中是否有该书籍信息
        LambdaQueryWrapper<Borrow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Borrow::getIsbn, isbn);
        List<Borrow> borrows = borrowMapper.selectList(queryWrapper);
        // 如果书籍信息存在于借阅表中，抛出业务异常，不允许删除
        if (!borrows.isEmpty()) {
            throw new AlreadyExistException(Excep.DELETE_BOOK_NOT_ALLOW);
        }
        bookMapper.deleteById(isbn);
    }
}
