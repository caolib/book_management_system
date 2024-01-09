package com.clb.service.Impl;

import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.domain.dto.ReaderDto;
import com.clb.domain.entity.Reader;
import com.clb.mapper.ReaderMapper;
import com.clb.service.ReaderService;
import com.clb.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderMapper readerMapper;

    @Override
    public Result<ReaderDto> login(ReaderDto reader) {
        String pwd = readerMapper.selectByName(reader.getUsername());
        //用户不存在
        if (pwd == null) {
            return Result.error(Excep.USER_NOT_EXIST);
        }
        //密码错误
        if (!pwd.equals(reader.getPassword())) {
            return Result.error(Excep.WRONG_PASSWORD);
        }

        //存在就生成令牌

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", reader.getUsername());
        reader.setToken(JwtUtils.generateJwt(claims));

        return Result.success(reader);
    }
}
