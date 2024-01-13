package com.clb.controller;

import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.exception.BaseException;
import com.clb.service.BorrowService;
import com.clb.util.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
@Slf4j
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /**
     * 查询用户借阅记录
     *
     * @param readerId 读者号
     */
    @GetMapping
    public Result<List<BorrowVo>> getBorrowByReaderId(Integer readerId) {
        log.info("readerId:{}", readerId);
        return borrowService.getBorrowByReaderId(readerId);
    }

    /**
     * 用户借阅图书
     *
     * @param isbn     书号
     * @param readerId 读者号
     * @param dueDate  应归还日期
     */
    @GetMapping("/borrowBook")
    public Result<String> borrow(String isbn, Integer readerId, String dueDate) {
        log.info("isbn:{} readerId:{} dueDate:{}", isbn, readerId, dueDate);

        if (!MyUtils.StrUtil(dueDate)) {
            throw new BaseException(Excep.RETURN_DATE_IS_NULL);
        }

        Date date = MyUtils.StrToDate(dueDate);
        return borrowService.borrow(isbn, readerId, date);
    }

    /**
     * 归还书籍
     *
     * @param id 借阅号
     */
    @GetMapping("/returnBook")
    public Result<String> returnBook(Integer id, String isbn) {
        log.info("returnBook id:{}", id);

        return borrowService.returnBook(id, isbn);
    }


    /**
     * 根据借阅号删除借阅记录
     * @param id 借阅号
     */
    @DeleteMapping
    public Result<String> deleteBorrow(Integer id) {
        log.info("deleteBorrow id:{}", id);

        return borrowService.deleteById(id);
    }

    /**
     * 批量删除借阅记录
     * @param ids 借阅号数组
     */
    @PostMapping("/batch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        log.info("ids:{}", ids);

        return borrowService.deleteBatchByIds(ids);
    }

}
