package com.clb.service.Impl;

import com.clb.constant.Excep;
import com.clb.constant.Jwt;
import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Admin;
import com.clb.domain.vo.AdminVo;
import com.clb.exception.AlreadyExistException;
import com.clb.exception.BaseException;
import com.clb.mapper.AdminMapper;
import com.clb.service.AdminService;
import com.clb.util.JwtUtils;
import com.clb.util.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    private final StringRedisTemplate redisTemplate;

    public AdminServiceImpl(AdminMapper adminMapper,StringRedisTemplate redisTemplate) {
        this.adminMapper = adminMapper;
        this.redisTemplate = redisTemplate;
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

        // 将令牌保存到redis中
        redisTemplate.opsForValue().set(token, token, Jwt.EXPIRE_TIME);

        // 封装信息
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(a, adminVo);
        adminVo.setToken(token);

        log.debug("a:{}", a);

        return Result.success(adminVo);
    }


    @Override
    public Result<String> register(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        // 用户名和密码不能为空
        if (!MyUtils.StrUtil(username) || !MyUtils.StrUtil(password)) {
            throw new BaseException(Excep.REGISTER_ERROR);
        }

        // 查询用户名是否已经存在
        Admin a = adminMapper.selectByUsername(username);
        if (a != null) {
            throw new AlreadyExistException(Excep.USER_ALREADY_EXIST);
        }

        adminMapper.insert(admin);

        return Result.success();
    }

    @Override
    public Result<String> updateById(Admin admin) {
        adminMapper.updateById(admin);

        return Result.success();
    }
}
