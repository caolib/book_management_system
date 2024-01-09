package com.clb.service;

import com.clb.domain.Result;
import com.clb.domain.dto.ReaderDto;

public interface ReaderService {

    Result<ReaderDto>login(ReaderDto reader);
}
