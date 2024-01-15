package com.clb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clb.domain.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT title FROM book WHERE ISBN = #{isbn}")
    String getTitleByIsbn(String isbn);

    @Update("UPDATE book SET number = number + #{num} WHERE ISBN = #{isbn}")
    void updateNumberByIsbn(String isbn,Integer num);

    @Select("SELECT COUNT(*) FROM book WHERE ISBN = #{isbn}")
    Long getByIsbn(String isbn);
}
