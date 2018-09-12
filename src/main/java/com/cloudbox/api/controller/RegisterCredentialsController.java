package com.cloudbox.api.controller;

import com.cloudbox.api.domain.request.LoginRequest;
import com.cloudbox.api.domain.response.BaseRS;
import com.cloudbox.api.domain.response.SuccessRS;
import com.cloudbox.api.service.auth.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@Slf4j
public class RegisterCredentialsController {
    @NotNull
    private LoginService loginService;


    //    public ResponseEntity<BaseRS> registration(@NotNull @RequestBody @Valid final BaseRS newAccount,
//                                                              @NotNull @RequestParam(name = "lang",
//                                                                      defaultValue = "") final String lang) {
    @GetMapping("/login")
    public ResponseEntity<BaseRS> registration(@RequestBody LoginRequest loginRequest) {
//        registrationService.createAccount(newAccount, LangHelper.toLang(lang));
        loginService.login(loginRequest);
        return ResponseEntity.ok(new SuccessRS());
    }
}
