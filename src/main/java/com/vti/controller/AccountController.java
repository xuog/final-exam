package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IAccountService service;

    @GetMapping
    public Page<AccountDTO> findAll(Pageable pageable, AccountFilterForm form){
        Page<Account> page = service.findAll(pageable, form);
        List<Account> accounts = page.getContent();
        List<AccountDTO> dtos = mapper.map(accounts, new TypeToken<List<AccountDTO>>(){}.getType());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable("id") int id){
        Account account = service.findById(id);
        AccountDTO dto = mapper.map(account, AccountDTO.class);
        return dto;
    }

    @PostMapping
    public void create(@RequestBody AccountCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody AccountUpdateForm form){
        form.setId(id);
        service.update(form);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id){
        service.deleteById(id);
    }
}
