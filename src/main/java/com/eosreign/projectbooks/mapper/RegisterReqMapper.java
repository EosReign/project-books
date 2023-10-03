package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.RegisterReq;
import com.eosreign.projectbooks.entity.Authority;
import com.eosreign.projectbooks.entity.Client;

public class RegisterReqMapper {
    public static Client toEntity(RegisterReq req) {
        Client entity = new Client();
        entity.setName(req.getName());
        entity.setAddress(req.getAddress());
        entity.setPhone(req.getPhone());
        entity.setPassword(req.getPassword());
        return entity;
    }

    public static Authority toAuthority(RegisterReq req) {
        Authority authority = new Authority();
        authority.setPhone(req.getPhone());
        authority.setRole("ROLE_USER");
        return authority;
    }
}
