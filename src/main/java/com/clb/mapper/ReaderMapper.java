package com.clb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clb.domain.entity.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReaderMapper extends BaseMapper<Reader> {
    @Select("select * from reader where username = #{username};")
    Reader selectByName(String username);
}
