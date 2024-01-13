package com.clb.service;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Reader;
import com.clb.domain.vo.ReaderVo;

public interface ReaderService {

    Result<ReaderVo>login(LoginDto reader);

    Result<Reader> updateReader(Reader reader);

    Result<String> register(Reader reader);
}
