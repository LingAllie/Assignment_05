package com.vti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping(value = "/{page}/{size}")
    public List<Department> getAllDepartments(@PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
        return departmentService.getAllDepartments(page, size);
    }

    @GetMapping(value = "/findById/{id}")
    public Department getDepartmentByID(@PathVariable(name = "id") int id) {
        return departmentService.getDepartmentByID(id);
    }

    @GetMapping(value = "/findByName/{name}")
    public List<Department> getDepartmentByName(@PathVariable(name = "name") String name) {
        return departmentService.getDepartmentByName(name);
    }

    @PostMapping("/createDepartment/{name}")
    public void createDepartment(@PathVariable(name = "name") String name) {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(name);
        departmentService.createDepartment(newDepartment);
    }

    @PutMapping(value = "/updateById/{id}/{newName}")
    public void updateDepartment(@PathVariable(name = "id") int id, @PathVariable(name = "newName") String name) {
        departmentService.updateDepartment(new Department(id, name));
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteDepartment(@PathVariable(name = "id") int id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping(value = "/count")
    public int getDepartmentCount() { return departmentService.getDepartmentCount(); }

    @GetMapping(value = "/checkExistByName/{name}")
    public boolean checkExistByName(@PathVariable(name = "name") String name) { return departmentService.isDepartmentExistsByName(name); }
}
