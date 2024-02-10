package com.clb;

import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BorrowTest {
    @Autowired
    private BorrowService borrowService;

    @Test
    public void testBorrowService() {
        Result<List<BorrowVo>> result = borrowService.getBorrowByReaderId();
        System.out.println(result.getData());
    }

    @Test
    public void testBorrowView() {
        borrowService.getBorrowByReaderId().getData().forEach(System.out::println);
    }
}