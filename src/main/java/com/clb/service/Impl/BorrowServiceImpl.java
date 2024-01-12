package com.clb.service.Impl;

import com.clb.constant.Excep;
import com.clb.domain.Borrow;
import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.exception.BaseException;
import com.clb.mapper.BookMapper;
import com.clb.mapper.BorrowMapper;
import com.clb.service.BorrowService;
import com.clb.util.MyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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

    /**
     * 读者借阅图书，向借阅表中插入记录的同时，更新图书的库存量，同时完成
     * 使用@Transactional让方法以事务方式执行
     */
    @Transactional
    @Override
    public Result<String> borrow(String isbn, Integer readerId, Date dueDate) {
        // 向借阅表中插入借阅记录
        Borrow borrow = Borrow.builder()
                .isbn(isbn)
                .dueDate(dueDate)
                .borrowDate(MyUtils.now())
                .readerId(String.valueOf(readerId))
                .build();

        borrowMapper.insert(borrow);

        // 更新图书库存-1
        bookMapper.updateNumberByIsbn(isbn,-1);

        return Result.success();
    }

    /**
     * 读者归还图书，向借阅表中插入归还日期，同时更新图书的库存量
     * 使用@Transactional让方法以事务方式执行
     */
    @Transactional
    @Override
    public Result<String> returnBook(Integer id,String isbn) {
        //更新借阅表中信息
        borrowMapper.updateReturnDateById(id, MyUtils.now());
        //同时更新图书库存+1
        bookMapper.updateNumberByIsbn(isbn, 1);

        return Result.success();
    }

    @Override
    public Result<String> deleteById(Integer id) {
        //根据id删除借阅记录
        borrowMapper.deleteById(id);

        return Result.success();
    }


    @Override
    public Result<String> deleteBatchByIds(List<Integer> ids) {
        borrowMapper.deleteBatchIds(ids);

        return Result.success();
    }

}
