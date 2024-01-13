package com.clb.service;

import com.clb.domain.Result;
import com.clb.domain.dto.LoginDto;
import com.clb.domain.vo.AdminVo;

public interface AdminService {

    Result<AdminVo> login(LoginDto admin);

    Result<String> updateNicknameById(Integer id, String nickname);

}
