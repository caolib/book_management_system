package com.clb.service.Impl;

import com.clb.constant.Common;
import com.clb.domain.Borrow;
import com.clb.domain.Result;
import com.clb.domain.vo.BorrowVo;
import com.clb.mapper.BookMapper;
import com.clb.mapper.BorrowMapper;
import com.clb.service.BorrowService;
import com.clb.util.MyUtils;
import com.clb.util.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;

    /**
     * 根据用户id查询借书记录
     */
    @Override
    public Result<List<BorrowVo>> getBorrowByReaderId() {
        // 从ThreadLocal中获取用户id
        Map<String, Object> reader = ThreadLocalUtil.get();
        log.debug("用户:{}", reader);
        Integer readerId = MyUtils.objToInt(reader.get(Common.ID));

        List<BorrowVo> result = borrowMapper.selectByReaderId(readerId);

        return Result.success(result);
    }

    /**
     * 读者借阅图书，向借阅表中插入记录的同时，更新图书的库存量，同时完成
     * 使用@Transactional让方法以事务方式执行
     */
    @Transactional
    @Override
    public Result<String> borrow(String isbn, Date dueDate) {
        Map<String, Object> reader = ThreadLocalUtil.get();
        String readerId = (String) reader.get(Common.ID);

        // 向借阅表中插入借阅记录
        Borrow borrow = Borrow.builder()
                .isbn(isbn)
                .dueDate(dueDate)
                .borrowDate(MyUtils.now())
                .readerId(readerId)
                .build();

        borrowMapper.insert(borrow);

        // 更新图书库存-1
        bookMapper.updateNumberByIsbn(isbn, -1);

        return Result.success();
    }

    /**
     * 读者归还图书，向借阅表中插入归还日期，同时更新图书的库存量
     * 使用@Transactional注解，让方法以事务方式执行，保证两个操作同时完成
     */
    @Transactional
    @Override
    public Result<String> returnBook(Integer id, String isbn) {
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
