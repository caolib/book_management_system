package com.clb.service;

import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;

import java.util.Date;
import java.util.List;

public interface BorrowService {


    Result<List<BorrowVo>> getBorrowByReaderId(Integer readerId);

    Result<String> borrow(String isbn, Integer readerId, Date dueDate);
}
