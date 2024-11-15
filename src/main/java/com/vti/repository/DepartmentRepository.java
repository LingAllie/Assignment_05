package com.vti.repository;

import com.vti.entity.Department;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository implements IDepartmentRepository {

    private HibernateUtils hibernateUtils;

    public DepartmentRepository() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public void createDepartment(Department department) {
        try (Session session = hibernateUtils.openSession()) {
            session.beginTransaction();
            if(!isDepartmentExistsByName(department.getDepartmentName())) {
                department.setDepartmentName(department.getDepartmentName().substring(0,1).toUpperCase() + department.getDepartmentName().substring(1).toLowerCase());
                session.save(department);
                session.getTransaction().commit();
                System.out.println("Department created successfully");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Department> getAllDepartments(int page, int size) {
        try (Session session = hibernateUtils.openSession()) {
            // create hql query
            Query<Department> query = session.createQuery("FROM Department");

            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);

            return query.list();
        }
    }

    public Department getDepartmentByID(int id) {
        try (Session session = hibernateUtils.openSession()) {
            return session.get(Department.class, id);
        }
    }

    public List<Department> getDepartmentByName(String name) {
        try (Session session = hibernateUtils.openSession()) {
            return session.createQuery("FROM Department WHERE departmentName LIKE :name", Department.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        }
    }

    public void updateDepartment(Department department) {
        try (Session session = hibernateUtils.openSession()) {
            session.beginTransaction();
            if(isDepartmentExistsByID(department.getDepartmentId())) {
                session.update(department);
                session.getTransaction().commit();
                System.out.println("Department name updated successfully");
            }
        }
    }

    public void deleteDepartment(int id) {
        try (Session session = hibernateUtils.openSession()) {
            session.beginTransaction();
            if (isDepartmentExistsByID(id)) {
                Department department = session.get(Department.class, id);
                session.delete(department);
                session.getTransaction().commit();
                System.out.println("Department deleted successfully");
            }
        }
    }

    public boolean isDepartmentExistsByID(int id) {
        try (Session session = hibernateUtils.openSession()) {
            return getDepartmentByID(id) != null;
        }
    }

    public boolean isDepartmentExistsByName(String name) {
        try (Session session = hibernateUtils.openSession()) {
            return session.createQuery("SELECT COUNT(*) > 0 FROM Department WHERE lower(trim(departmentName)) = :name", Boolean.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    public int getDepartmentCount() {
        try (Session session = hibernateUtils.openSession()) {
            // Correct the query by returning the count as Long
            return ((Long) session.createQuery("SELECT COUNT(*) FROM Department", Long.class).uniqueResult()).intValue();
        }
    }


}
