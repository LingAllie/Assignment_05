package com.vti.service;

import java.util.List;

import com.vti.entity.Department;

public interface IDepartmentService {

    public List<Department> getAllDepartments(int page, int size);

    public Department getDepartmentByID(int id);

    public List<Department> getDepartmentByName(String name);

    public void createDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(int id);

    public int getDepartmentCount();

    public boolean isDepartmentExistsByName(String name);
}
