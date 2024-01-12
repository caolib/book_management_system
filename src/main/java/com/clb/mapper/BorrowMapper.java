package com.clb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clb.domain.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
    @Select("SELECT * FROM borrow WHERE reader_id = #{readerId}")
    List<Borrow> selectByReaderId(Integer readerId);

    void updateReturnDateById(Integer id, Date now);
}
