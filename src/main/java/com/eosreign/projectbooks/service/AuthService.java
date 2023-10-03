package com.eosreign.projectbooks.service;


import com.eosreign.projectbooks.dto.LoginReq;
import com.eosreign.projectbooks.dto.RegisterReq;

public interface AuthService {
    LoginReq login(LoginReq req);
    RegisterReq register(RegisterReq registerReq);
}
