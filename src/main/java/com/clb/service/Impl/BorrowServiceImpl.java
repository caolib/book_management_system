package com.clb.service.Impl;

import com.clb.domain.Borrow;
import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.mapper.BookMapper;
import com.clb.mapper.BorrowMapper;
import com.clb.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;

    @Override
    public Result<List<BorrowVo>> getBorrowByReaderId(Integer readerId) {
        List<Borrow> borrows = borrowMapper.selectByReaderId(readerId);
        List<BorrowVo> result = new ArrayList<>();
        //拷贝到结果
        borrows.forEach(borrow -> {
            BorrowVo borrowVo = new BorrowVo();
            BeanUtils.copyProperties(borrow, borrowVo);
            result.add(borrowVo);
        });

        //根据isbn查询所有图书书名
        for (BorrowVo borrowVo : result) {
            String isbn = borrowVo.getIsbn();
            borrowVo.setBookName(bookMapper.getTitleByIsbn(isbn));
            borrowVo.setStatus(borrowVo.getReturnDate() != null);
        }

        return Result.success(result);
    }

    @Override
    public Result<String> borrow(String isbn, Integer readerId, Date dueDate) {
        //todo 解决错误添加信息
        Borrow borrow = Borrow.builder()
                .isbn(isbn)
                .returnDate(dueDate)
                .borrowDate(new Date())
                .readerId(String.valueOf(readerId))
                .build();

        borrowMapper.insert(borrow);

        return Result.success();
    }

}
