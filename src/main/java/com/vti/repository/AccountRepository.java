package com.vti.repository;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    private HibernateUtils instance;

    public AccountRepository() {
        instance = HibernateUtils.getInstance();
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAllAccounts() {
        try (Session session = instance.openSession()) {
            return session.createQuery("FROM Account").list();
        }
    }

    public void createAccount(Account account) {
        try (Session session = instance.openSession()) {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            System.out.println("Account created successfully");
        }
    }

    public Account getAccountByID(int id) {
        try (Session session = instance.openSession()) {
            session.beginTransaction();
            Account account = session.get(Account.class, id);
            session.getTransaction().commit();
            return account;
        }
    }

    public List<Account> getAccountByFullName(String username) {
        try (Session session = instance.openSession()) {
            return session.createQuery("FROM Account WHERE fullName = :fullname", Account.class)
                    .setParameter("fullname", "%" + username + "%")
                    .list();
        }
    }

    public void updateAccount(Account account) {
        try (Session session = instance.openSession()) {
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
            System.out.println("Account updated successfully");
        }
    }

    public void deleteAccount(int id) {
        try (Session session = instance.openSession()) {
            session.beginTransaction();
            Account account = session.get(Account.class, id);
            session.delete(account);
            session.getTransaction().commit();
            System.out.println("Account deleted successfully");
        }
    }

    public boolean isAccountExistsByID(int id) {
        try (Session session = instance.openSession()) {
            return getAccountByID(id) != null;
        }
    }

    public boolean isAccountExistsByFullName(String username) {
        try (Session session = instance.openSession()) {
            return getAccountByFullName(username) != null;
        }
    }
}
