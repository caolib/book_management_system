package com.clb.service.Impl;

import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Reader;
import com.clb.domain.vo.ReaderVo;
import com.clb.exception.AlreadyExistException;
import com.clb.mapper.ReaderMapper;
import com.clb.service.ReaderService;
import com.clb.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderMapper readerMapper;

    @Override
    public Result<ReaderVo> login(LoginDto reader) {
        Reader r = readerMapper.selectByName(reader.getUsername());
        //用户不存在
        if (r == null) {
            return Result.error(Excep.USER_NOT_EXIST);
        }
        String pwd = r.getPassword();
        //密码错误
        if (!pwd.equals(reader.getPassword())) {
            return Result.error(Excep.WRONG_PASSWORD);
        }

        //生成令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("clibin", reader.getUsername());
        String token = JwtUtils.generateJwt(claims);

        // 封装信息
        ReaderVo readerVo = new ReaderVo();
        readerVo.setToken(token);
        BeanUtils.copyProperties(r, readerVo);

        return Result.success(readerVo);
    }

    @Override
    public Result<Reader> updateReader(Reader reader) {
        readerMapper.updateById(reader);
        return Result.success();
    }
}
