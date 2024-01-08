package com.clb.mapper;

import com.clb.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admin")
    List<Admin> getAll();
}
