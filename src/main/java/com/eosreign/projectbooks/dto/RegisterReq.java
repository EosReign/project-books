package com.eosreign.projectbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterReq {
    private String name;
    private String address;
    private Long phone;
    private String password;
}
