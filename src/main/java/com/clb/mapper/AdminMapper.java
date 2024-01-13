package com.clb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clb.domain.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * FROM admin")
    List<Admin> getAll();

    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin selectByUsername(String username);

    @Update("UPDATE admin SET nickname = #{nickname} WHERE id = #{id}")
    void updateNicknameById(@Param("id") Integer id, @Param("nickname") String nickname);
}
