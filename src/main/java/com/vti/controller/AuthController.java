package com.vti.controller;

import com.vti.dto.ProfileDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AuthUpdateForm;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IAccountService service;

    @PostMapping("/register")
    public void register(@RequestBody AccountCreateForm form) {
        service.create(form);
    }

    @GetMapping("/login")
    // lấy ra thông tin cá nhân người dùng đã đăng nhập thành công
    // principal: chứa username mà đăng nhập thành công trước đó
    public ProfileDTO getProfile(Principal principal) {
        String username = principal.getName();
        Account account = service.findByUsername(username);
        return mapper.map(account, ProfileDTO.class);
    }
    @PutMapping("/change")
    public void update(@RequestBody AuthUpdateForm form){
        service.update(form);
    }
}

