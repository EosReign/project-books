package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.LoginReq;
import com.eosreign.projectbooks.dto.RegisterReq;
import com.eosreign.projectbooks.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<LoginReq> loginPost(@RequestBody LoginReq req) {
        log.info("Запускается метод Login. ");
        return ResponseEntity.ok(authService.login(req));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<RegisterReq> register(@RequestBody RegisterReq req) {
        log.info("Запускается метод Register. ");
        return ResponseEntity.ok(authService.register(req));
    }
}
