package com.clb.controller;

import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
@Slf4j
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public Result<List<BorrowVo>> getBorrowByReaderId(Integer readerId) {
        log.info("readerId:{}", readerId);
        return borrowService.getBorrowByReaderId(readerId);
    }

    @GetMapping("/borrowBook")
    public Result<String> borrow(String isbn, Integer readerId, String dueDate) {
        log.info("isbn:{}", isbn);
        log.info("readerId:{}", readerId);
        log.info("dueDate:{}", dueDate);

        // 日期格式转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDueDate = new Date();
        try {
            parsedDueDate = format.parse(dueDate);
        } catch (ParseException e) {
            //todo 补充信息
            return Result.error("");
        }
        return  borrowService.borrow(isbn,readerId,parsedDueDate);
    }

}
