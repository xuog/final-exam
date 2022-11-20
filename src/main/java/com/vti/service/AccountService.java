package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecification;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AccountService implements IAccountService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IAccountRepository repository;
    @Override
    public Page<Account> findAll(Pageable pageable , AccountFilterForm form) {
        Specification spec = AccountSpecification.builSpec(form);
        return repository.findAll(spec,pageable);
    }

    @Override
    public Account findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(AccountCreateForm form) {
        Account account = mapper.map(form, Account.class);
        repository.save(account);
    }

    @Override
    public void update(AccountUpdateForm form) {

        Account account= mapper.map(form, Account.class);
        repository.save(account);
    }


    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
