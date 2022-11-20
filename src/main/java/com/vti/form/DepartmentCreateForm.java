package com.vti.form;

import com.vti.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateForm {
    private String name;
    private Integer totalMembers;
    private Department.Type type;
}
