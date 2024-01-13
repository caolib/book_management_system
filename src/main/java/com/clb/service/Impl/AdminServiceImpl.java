package com.clb.service.Impl;

import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Admin;
import com.clb.domain.vo.AdminVo;
import com.clb.mapper.AdminMapper;
import com.clb.service.AdminService;
import com.clb.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Result<AdminVo> login(LoginDto admin) {
        Admin a = adminMapper.selectByUsername(admin.getUsername());
        //用户不存在
        if (a == null) {
            return Result.error(Excep.USER_NOT_EXIST);
        }
        String pwd = a.getPassword();

        //密码错误
        if (!Objects.equals(pwd, admin.getPassword())) {
            return Result.error(Excep.WRONG_PASSWORD);
        }

        //生成令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", admin.getUsername());
        String token = JwtUtils.generateJwt(claims);

        // 封装信息
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(a, adminVo);
        adminVo.setToken(token);

        log.debug("a:{}", a);

        return Result.success(adminVo);
    }

    @Override
    public Result<String> updateNicknameById(Integer id, String nickname) {
        adminMapper.updateNicknameById(id,nickname);

        return Result.success();
    }
}
