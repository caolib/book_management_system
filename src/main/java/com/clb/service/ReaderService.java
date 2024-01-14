package com.clb.service;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.entity.Reader;
import com.clb.domain.vo.ReaderVo;

import java.util.List;

public interface ReaderService {

    Result<ReaderVo>login(LoginDto reader);

    Result<Reader> updateReader(Reader reader);

    Result<String> register(Reader reader);

    Result<List<Reader>> getAllReader(Reader condition);

    Result<String> deleteById(Integer id);
}
