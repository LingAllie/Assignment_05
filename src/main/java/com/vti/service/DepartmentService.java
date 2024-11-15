package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(int page, int size) {
        return departmentRepository.getAllDepartments(page,size);
    }

    public Department getDepartmentByID(int id) {
        return departmentRepository.getDepartmentByID(id);
    }

    public List<Department> getDepartmentByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }

    public void createDepartment(Department department) {
        departmentRepository.createDepartment(department);
    }

    public void updateDepartment(Department department) {
        departmentRepository.updateDepartment(department);
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteDepartment(id);
    }

    public boolean isDepartmentExistsByID(int id) {
        return departmentRepository.isDepartmentExistsByID(id);
    }

    public boolean isDepartmentExistsByName(String name) {
        return departmentRepository.isDepartmentExistsByName(name);
    }

    public int getDepartmentCount() { return departmentRepository.getDepartmentCount(); }
}
