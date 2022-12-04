package com.vti.service;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    Page<Department> findAll(Pageable pageable, DepartmentFilterForm form);

    Department findById(int id);

 //   Department findByName(String name);

    void create(DepartmentCreateForm form);

    void update(DepartmentUpdateForm form);

    void deleteAllById(List<Integer> ids);

//    void deleteByName(String name);

}
